package flink_1_9.runtime.testing;

import com.sun.rowset.internal.Row;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.functions.AsyncTableFunction;
import org.apache.flink.table.functions.FunctionContext;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.table.sources.LookupableTableSource;
import org.apache.flink.table.sources.StreamTableSource;
import org.apache.flink.table.types.utils.TypeConversions;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/22 10:59
 * @Description
 */
public class LookUpAsyncTest {

    @Test
    public void test() throws Exception {
        LookUpAsyncTest.main(new String[]{});
    }

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //env.setParallelism(1);
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

        final ParameterTool params = ParameterTool.fromArgs(args);
        String fileName = params.get("f");
        DataStream<String> source = env.readTextFile("hdfs://172.16.44.28:8020" + fileName, "UTF-8");

        TypeInformation[] types = new TypeInformation[]{Types.STRING, Types.STRING, Types.LONG};
        String[] fields = new String[]{"id", "user_click", "time"};
        RowTypeInfo typeInformation = new RowTypeInfo(types, fields);

        DataStream<Row> stream = source.map(new MapFunction<String, Row>() {
            private static final long serialVersionUID = 2349572543469673349L;

            @Override
            public Row map(String s) {
                String[] split = s.split(",");
                Row row = new Row(split.length);
                for (int i = 0; i < split.length; i++) {

                    Object value = split[i];
                    if (types[i].equals(Types.STRING)) {
                        value = split[i];
                    }
                    if (types[i].equals(Types.LONG)) {
                        value = Long.valueOf(split[i]);
                    }
                    row.setField(i, value);
                }
                return row;
            }
        }).returns(typeInformation);

        tableEnv.registerDataStream("user_click_name", stream, String.join(",", typeInformation.getFieldNames()) + ",proctime.proctime");

        MyAsyncLookupFunction.RedisAsyncLookupTableSource tableSource = MyAsyncLookupFunction.RedisAsyncLookupTableSource.Builder.newBuilder()
                .withFieldNames(new String[]{"id", "name"})
                .withFieldTypes(new TypeInformation[]{Types.STRING, Types.STRING})
                .build();
        tableEnv.registerTableSource("info", tableSource);

        String sql = "select t1.id,t1.user_click,t2.name" +
                " from user_click_name as t1" +
                " join info FOR SYSTEM_TIME AS OF t1.proctime as t2" +
                " on t1.id = t2.id";

        Table table = tableEnv.sqlQuery(sql);

        DataStream<Row> result = tableEnv.toAppendStream(table, Row.class);

        DataStream<String> printStream = result.map(new MapFunction<Row, String>() {
            @Override
            public String map(Row value) throws Exception {
                return value.toString();
            }
        });

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9094");
        FlinkKafkaProducer011<String> kafkaProducer = new FlinkKafkaProducer011<>(
                "user_click_name",
                new SimpleStringSchema(),
                properties);
        printStream.addSink(kafkaProducer);

        tableEnv.execute(Thread.currentThread().getStackTrace()[1].getClassName());
    }
    public class MyAsyncLookupFunction extends AsyncTableFunction<Row> {

        private final String[] fieldNames;
        private final TypeInformation[] fieldTypes;

        private transient RedisAsyncCommands<String, String> async;

        public MyAsyncLookupFunction(String[] fieldNames, TypeInformation[] fieldTypes) {
            this.fieldNames = fieldNames;
            this.fieldTypes = fieldTypes;
        }

        @Override
        public void open(FunctionContext context) {
            //配置redis异步连接
            RedisClient redisClient = RedisClient.create("redis://127.0.0.1:6379");
            StatefulRedisConnection<String, String> connection = redisClient.connect();
            async = connection.async();
        }

        //每一条流数据都会调用此方法进行join
        public void eval(CompletableFuture<Collection<Row>> future, Object... paramas) {
            //表名、主键名、主键值、列名
            String[] info = {"userInfo", "userId", paramas[0].toString(), "userName"};
            String key = String.join(":", info);
            RedisFuture<String> redisFuture = async.get(key);

            redisFuture.thenAccept(new Consumer<String>() {
                @Override
                public void accept(String value) {
                    future.complete(Collections.singletonList(Row.of(key, value)));
                    //todo
//                BinaryRow row = new BinaryRow(2);
                }
            });
        }

        @Override
        public TypeInformation<Row> getResultType() {
            return new RowTypeInfo(fieldTypes, fieldNames);
        }

        public static final class Builder {
            private String[] fieldNames;
            private TypeInformation[] fieldTypes;

            private Builder() {
            }

            public static Builder getBuilder() {
                return new Builder();
            }

            public Builder withFieldNames(String[] fieldNames) {
                this.fieldNames = fieldNames;
                return this;
            }

            public Builder withFieldTypes(TypeInformation[] fieldTypes) {
                this.fieldTypes = fieldTypes;
                return this;
            }

            public MyAsyncLookupFunction build() {
                return new MyAsyncLookupFunction(fieldNames, fieldTypes);
            }
        }

    public class RedisAsyncLookupTableSource implements StreamTableSource<Row>, LookupableTableSource<Row> {

        private final String[] fieldNames;
        private final TypeInformation[] fieldTypes;

        public RedisAsyncLookupTableSource(String[] fieldNames, TypeInformation[] fieldTypes) {
            this.fieldNames = fieldNames;
            this.fieldTypes = fieldTypes;
        }

        //同步方法
        @Override
        public TableFunction<Row> getLookupFunction(String[] strings) {
            return null;
        }

        //异步方法
        @Override
        public AsyncTableFunction<Row> getAsyncLookupFunction(String[] strings) {
            return MyAsyncLookupFunction.Builder.getBuilder()
                    .withFieldNames(fieldNames)
                    .withFieldTypes(fieldTypes)
                    .build();
        }

        //开启异步
        @Override
        public boolean isAsyncEnabled() {
            return true;
        }

        @Override
        public DataType getProducedDataType() {
            return TypeConversions.fromLegacyInfoToDataType(new RowTypeInfo(fieldTypes, fieldNames));
        }

        @Override
        public TableSchema getTableSchema() {
            return TableSchema.builder()
                    .fields(fieldNames, TypeConversions.fromLegacyInfoToDataType(fieldTypes))
                    .build();
        }

        @Override
        public DataStream<Row> getDataStream(StreamExecutionEnvironment environment) {
            throw new UnsupportedOperationException("do not support getDataStream");
        }

        public static final class Builder {
            private String[] fieldNames;
            private TypeInformation[] fieldTypes;

            private Builder() {
            }

            public static Builder newBuilder() {
                return new Builder();
            }

            public Builder withFieldNames(String[] fieldNames) {
                this.fieldNames = fieldNames;
                return this;
            }

            public Builder withFieldTypes(TypeInformation[] fieldTypes) {
                this.fieldTypes = fieldTypes;
                return this;
            }

            public RedisAsyncLookupTableSource build() {
                return new RedisAsyncLookupTableSource(fieldNames, fieldTypes);
            }
        }

}
