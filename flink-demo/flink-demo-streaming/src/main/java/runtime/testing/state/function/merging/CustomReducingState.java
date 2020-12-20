package runtime.testing.state.function.merging;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ReducingState;
import org.apache.flink.api.common.state.ReducingStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/18 20:30
 * @Description
 */
public class CustomReducingState extends RichFlatMapFunction<Tuple2<String, Long>, Tuple2<String, Long>> {

    // managed keyed state
    // 用于保存每一个 key 对应的 value 的总值
    private ReducingState<Long> sumState;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        // 注册状态 
        ReducingStateDescriptor<Long> descriptor = new ReducingStateDescriptor<>("sum",
                new ReduceFunction<Long>() {
                    @Override
                    public Long reduce(Long value1, Long value2) throws Exception {
                        return value1 + value2;
                    }
                }, Long.class
        );
        this.sumState = getRuntimeContext().getReducingState(descriptor);
    }

    @Override
    public void flatMap(Tuple2<String, Long> value, Collector<Tuple2<String, Long>> out) throws Exception {
        //  将数据放到状态中
        this.sumState.add(value.f1);

        out.collect(Tuple2.of(value.f0, sumState.get()));
    }
}
