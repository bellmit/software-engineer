package runtime.utils;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.util.serialization.KeyedDeserializationSchema;
import runtime.pojo.KafkaMsg;
import runtime.pojo.Version1Pojo;
import runtime.pojo.schema.Version1PojoSchema;

import java.io.IOException;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @Description
 * @date 2020/5/7 9:13
 */
public class CustomKafkaConsumer {
    /*public static <T> DataStreamSource<KafkaMsg> getResultMethod(StreamExecutionEnvironment env, String[] args, String topic, int num) {

        args = new String[]{"--input-topic", topic, "--bootstrap.servers", "node01:9092,node02:9092,node03:9092", "--zookeeper.connect", "node01:2181,node02:2181,node03:2181", "--group.id", "FD-Java-DDD-1-5"};

        final ParameterTool parameterTool = ParameterTool.fromArgs(args);

        return env.addSource(new FlinkKafkaConsumer09<>(
                parameterTool.getRequired("input-topic"),
                new CustomKeyedDeserializationSchena(),
                //new SimpleStringSchema(),
                parameterTool.getProperties())
                .setStartFromGroupOffsets()

                //.setStartFromEarliest()
                //.setStartFromSpecificOffsets()
                .setStartFromLatest());
    }*/

    public static DataStream<String> getResultMethod(StreamExecutionEnvironment env, String[] args, String topic) {

        args = new String[]{"--input-topic", topic, "--bootstrap.servers", "node01:9092,node02:9092,node03:9092", "--zookeeper.connect", "node01:2181,node02:2181,node03:2181", "--group.id", "FD-Java-DDD-1-5"};

        final ParameterTool parameterTool = ParameterTool.fromArgs(args);

        return env.addSource(new FlinkKafkaConsumer09<>(
                parameterTool.getRequired("input-topic"),
                //new CustomKeyedDeserializationSchena(),
                new SimpleStringSchema(),
                parameterTool.getProperties())
                .setStartFromGroupOffsets()

                .setStartFromLatest());
    }


    public static DataStream<Version1Pojo> getResultMethod(StreamExecutionEnvironment env, String[] args, String topic, String Pojo) {

        args = new String[]{"--input-topic", topic, "--bootstrap.servers", "node01:9092,node02:9092,node03:9092", "--zookeeper.connect", "node01:2181,node02:2181,node03:2181", "--group.id", "FD-Java-DDD-1-5"};

        final ParameterTool parameterTool = ParameterTool.fromArgs(args);


        return env.addSource(new FlinkKafkaConsumer09<>(
                parameterTool.getRequired("input-topic"),
                //new CustomKeyedDeserializationSchena(),
                new Version1PojoSchema(),
                parameterTool.getProperties())
                .setStartFromGroupOffsets()

                //.setStartFromEarliest()
                //.setStartFromSpecificOffsets()
                .setStartFromLatest()).setParallelism(3);
    }


    public static class CustomKeyedDeserializationSchena implements KeyedDeserializationSchema<KafkaMsg> {
        @Override
        public KafkaMsg deserialize(byte[] messageKey, byte[] message, String topic, int partition, long offset) throws IOException {

            return new KafkaMsg(
                    Version1Pojo.jsonFromClass(new String(message)),
                    topic,
                    partition,
                    offset);
        }

        @Override
        public boolean isEndOfStream(KafkaMsg nextElement) {
            return false;
        }

        @Override
        public TypeInformation<KafkaMsg> getProducedType() {
            return TypeInformation.of(KafkaMsg.class);
        }
    }

}
