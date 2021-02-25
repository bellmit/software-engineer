package runtime.jar;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import runtime.testing.state.function.merging.CustomAggregatingState;

import java.util.Random;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/8 21:21
 * @Description
 */
public class StateCountDemo {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());

        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.DELETE_ON_CANCELLATION);

//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //env.setStateBackend(new FsStateBackend("hdfs://cdhbds/Flink-checkpoint/Px/demo/v1"));
        env.setStateBackend(new FsStateBackend("hdfs://node01:8020/Flink-checkpoint/Px/demo/v1"));
        //env.getCheckpointConfig().setCheckpointTimeout(5000);

        // hdfs://node01:8020/Flink-checkpoint/Px/atm
        env.enableCheckpointing(50000);

        /**
         * hadoop
         * hadoop
         * hadoop
         */

        // socketTextStream = 1 map = 8 keyBy = 8 flatMap = 8 print = 8
        env.socketTextStream("node01", 6657)
                .map(new MapFunction<String, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> map(String value) throws Exception {
                        return Tuple2.of(value, new Random().nextLong());
                    }
                }).uid("o1")
                .keyBy(0)
                .flatMap(new CustomAggregatingState()).uid("o2")
                .print().uid("o4");


        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
