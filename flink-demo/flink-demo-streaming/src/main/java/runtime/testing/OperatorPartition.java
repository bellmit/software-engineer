package runtime.testing;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.netty4.io.netty.handler.ssl.SniCompletionEvent;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.util.Collector;
import runtime.pojo.Version1Pojo;
import runtime.utils.datasource.CustomKafkaConsumer;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/1/2 14:11
 * @Description
 */
public class OperatorPartition {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());

        env.setParallelism(3);
        CustomKafkaConsumer.getResultMethod(env, args, "DDL", "")
              //  .keyBy(Version1Pojo::getUserId)
//                .process(new ProcessFunction<Version1Pojo, Object>() {
//                    @Override
//                    public void processElement(Version1Pojo value, Context ctx, Collector<Object> out) throws Exception {
//                        out.collect(value);
//                    }
//                })
                .flatMap(new FlatMapFunction<Version1Pojo, Object>() {
                    @Override
                    public void flatMap(Version1Pojo value, Collector<Object> out) throws Exception {
                        out.collect(value);
                    }
                })
                .addSink(new RichSinkFunction<Object>() {
                    @Override
                    public void invoke(Object value) throws Exception {
                        System.out.println(value);
                    }
                }).setParallelism(7);

        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
