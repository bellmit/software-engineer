package runtime.testing.state;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import runtime.testing.state.function.CustomMapState;
import runtime.testing.state.function.CustomValueState;
import runtime.testing.state.function.merging.CustomAggregatingState;
import runtime.testing.state.function.merging.CustomListState;
import runtime.testing.state.function.merging.CustomReducingState;

import java.util.Random;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/8 21:21
 * @Description
 */
public class StateCountDemo {
    public static void main(String[] args) {

        //StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStateBackend(new FsStateBackend("hdfs://node01:8020/Flink-checkpoint/Px/demo/v1"));
        //env.getCheckpointConfig().setCheckpointTimeout(5000);

        // hdfs://node01:8020/Flink-checkpoint/Px/atm
        env.enableCheckpointing(5000);

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
                })

                .keyBy(0)
                /**
                 * Value State
                 */
                //.flatMap(new CustomValueState());

                /**
                 * ListState
                 */
                //.flatMap(new CustomListState());

                /**
                 * MapState
                 */
                //.flatMap(new CustomMapState());

                /**
                 * ReducingState
                 */
                //.flatMap(new CustomReducingState())

                /**
                 * AggregatingState
                 */
                .flatMap(new CustomAggregatingState())


                .print();


        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
