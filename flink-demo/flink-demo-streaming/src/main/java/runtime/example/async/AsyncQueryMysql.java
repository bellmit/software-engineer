package runtime.example.async;

import lombok.SneakyThrows;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import org.apache.hadoop.mapreduce.ID;
import runtime.utils.Singleton;
import runtime.utils.datasource.CustomSocketStream;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/22 16:02
 * @Description
 */
public class AsyncQueryMysql {
    final static String SQL = "SELECT * FROM `user_info` WHERE id = ";

    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        env.setParallelism(3);
        DataStreamSource<String> stream = CustomSocketStream.getSocketTestData(env, "node01", 6777);


        SingleOutputStreamOperator<String> operator = AsyncDataStream
                .unorderedWait(
                        stream,
                        new CustomAsyncQueryMysql(),
                        50,
                        TimeUnit.SECONDS,
                        100
                );
        operator.print();


        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class CustomAsyncQueryMysql extends RichAsyncFunction<String, String> {
        private Connection conn;

        @Override
        public void open(Configuration parameters) throws Exception {
            super.open(parameters);
            this.conn = Singleton.SINGLETON.getInstance().getConnection();
            conn.setAutoCommit(false);
        }

        @Override
        public void close() throws Exception {
            super.close();
            if (conn != null) {
                conn.close();
            }
        }

        @Override
        public void asyncInvoke(String input, ResultFuture<String> resultFuture) throws Exception {
            String result = "";
            Statement statement = this.conn.createStatement();
            ResultSet query = statement.executeQuery(SQL + input);
            if (query != null && query.next()) {
                result = input + " info Name " + query.getString("name");
            } else {
                result = input + " info Name " + "Null Point";
            }

            resultFuture.complete(Collections.singleton(result));

        }

        @Override
        public void timeout(String input, ResultFuture<String> resultFuture) throws Exception {
            System.out.println("超时");

        }
    }

}
