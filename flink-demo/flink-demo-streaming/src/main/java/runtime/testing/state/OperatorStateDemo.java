package runtime.testing.state;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.checkpoint.ListCheckpointed;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import runtime.utils.datasource.CustomSocketStream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/17 11:17
 * @Description
 */
public class OperatorStateDemo {

    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //env.setStateBackend(new FsStateBackend("hdfs://node01:8020/Flink-checkpoint/Px/sofware-engineer/000001"));

        CustomSocketStream.getSocketTestData(env, "node01", 6777)
                .map(new MapFunction<String, String>() {
                    @Override
                    public String map(String value) throws Exception {
                        return value;
                    }
                }).process(new CustomOperatorStateFunction(8)).setParallelism(1);

        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static class CustomOperatorStateFunction extends ProcessFunction<String, Object> implements CheckpointedFunction {
        // 用于缓存结果数据的
        //private List<HashMap<String, Integer>> bufferElements;
        private List<Tuple2<String, Integer>> bufferElements;
        // 表示内存中数据的大小阈值
        private int threshold;
        // 用于保存内存中的状态信息
        private ListState<Tuple2<String, Integer>> checkpointState;

        public CustomOperatorStateFunction(int threshold) {
            this.threshold = threshold;
            this.bufferElements = new ArrayList<>(8);
        }

        @Override
        public void snapshotState(FunctionSnapshotContext context) throws Exception {
            checkpointState.clear();
            for (Tuple2<String, Integer> element : bufferElements) {
                checkpointState.add(element);
            }
        }

        @Override
        public void initializeState(FunctionInitializationContext context) throws Exception {

            ListStateDescriptor<Tuple2<String, Integer>> descriptor =
                    new ListStateDescriptor<>(
                            "listState",
                            TypeInformation.of(new TypeHint<Tuple2<String, Integer>>() {
                            }));

            // 注册一个 state
            this.checkpointState = context.getOperatorStateStore().getListState(descriptor);

            if (context.isRestored()) {
                for (Tuple2<String, Integer> hashMap : this.checkpointState.get()) {
                    bufferElements.add(hashMap);
                }
            }
        }

        @Override
        public void processElement(String value, Context ctx, Collector<Object> out) throws Exception {

            String UK = value + "|||";


            boolean flag = false;

            if (bufferElements.size() == 0) {
                bufferElements.add(new Tuple2<>(value, 1));
            } else {
                for (Tuple2<String, Integer> element : bufferElements) {
                    if (element.f0.equals(value)) {
                        flag = true;
                        element.f1 = element.f1 + 999;
                        break;
                    }
                }
                if (!flag) {
                    bufferElements.add(new Tuple2<>(value, 1));
                }
            }

            for (Tuple2<String, Integer> tuple2 : bufferElements) {

                System.out.println(tuple2);
            }
        }
    }

    public static class CustomOperatorListStateFunction extends ProcessFunction<String, Object> implements ListCheckpointed<Serializable>{
        @Override
        public List snapshotState(long checkpointId, long timestamp) throws Exception {
            return null;
        }

        @Override
        public void restoreState(List state) throws Exception {

        }

        @Override
        public void processElement(String value, Context ctx, Collector<Object> out) throws Exception {

        }
    }
}

