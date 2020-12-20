package runtime.testing.state.function.merging;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.AggregatingState;
import org.apache.flink.api.common.state.AggregatingStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/18 20:32
 * @Description
 */
public class CustomAggregatingState extends RichFlatMapFunction<Tuple2<String, Long>, Tuple2<String, String>> {

    private AggregatingState<Long, String> totalStr;
    final private String contains = "Contains: ";

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        AggregatingStateDescriptor<Long, String, String> descriptor = new AggregatingStateDescriptor<>("totalStr",
                new AggregateFunction<Long, String, String>() {
                    @Override
                    public String createAccumulator() {
                        return contains;
                    }

                    @Override
                    public String add(Long value, String accumulator) {

                        return (contains.equals(accumulator))
                                ? accumulator + value
                                : accumulator + " and " + value;
                    }

                    @Override
                    public String getResult(String accumulator) {
                        return accumulator;
                    }

                    @Override
                    public String merge(String a, String b) {
                        return a + " and " + b;
                    }
                }, String.class
        );

        this.totalStr = getRuntimeContext().getAggregatingState(descriptor);

    }

    @Override
    public void flatMap(Tuple2<String, Long> value, Collector<Tuple2<String, String>> out) throws Exception {
        this.totalStr.add(value.f1);
        out.collect(Tuple2.of(value.f0, totalStr.get()));
    }
}
