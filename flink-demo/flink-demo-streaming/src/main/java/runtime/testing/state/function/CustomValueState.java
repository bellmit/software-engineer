package runtime.testing.state.function;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/18 20:30
 * @Description
 */
public class CustomValueState extends RichFlatMapFunction<Tuple2<String, Long>, Tuple2<String, Long>> {

    private ValueState<Tuple2<String, Long>> tempValueState;


    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

        ValueStateDescriptor<Tuple2<String, Long>> descriptor = new ValueStateDescriptor<>(
                "tempValueState", Types.TUPLE(Types.STRING, Types.LONG));

        this.tempValueState = getRuntimeContext().getState(descriptor);

    }


    @Override
    public void flatMap(Tuple2<String, Long> value, Collector<Tuple2<String, Long>> out) throws Exception {


        Tuple2<String, Long> thisState = this.tempValueState.value() == null
                        ? new Tuple2(value.f0, 0L)
                        : this.tempValueState.value();
        thisState.f1 += 1;
        this.tempValueState.update(thisState);

    }


     /*.flatMap(new RichFlatMapFunction<Tuple2<String, Long>, Long>() {
                    ValueState<Tuple2<Long, Long>> tempState;
                    Tuple2<Long, Long> tuple2;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        this.tempState = getRuntimeContext().getState(new ValueStateDescriptor<Tuple2<Long, Long>>("tempState",
                                Types.TUPLE(Types.LONG, Types.LONG)));
                    }

                    @Override
                    public void flatMap(Tuple2<String, Long> value, Collector<Long> out) throws Exception {


                        this.tuple2 = (this.tempState.value() == null) ? Tuple2.of(0L, 0L) : this.tempState.value();

                        tuple2.f0++;
                        tuple2.f1 += value.f1;

                        this.tempState.update(tuple2);

                        if (tuple2.f0 >= 3) {
                            out.collect(tuple2.f1 / 3);
                            this.tempState.clear();
                        }
                    }
                })*/

}
