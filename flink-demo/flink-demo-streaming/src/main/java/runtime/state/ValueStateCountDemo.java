package runtime.state;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Random;
import java.util.UnknownFormatConversionException;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/8 21:21
 * @Description
 */
public class ValueStateCountDemo {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        /**
         * hadoop
         * hadoop
         * hadoop
         */
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
                .flatMap(new RichFlatMapFunction<Tuple2<String, Long>, Long>() {
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
                })
                /**
                 * ListState
                 */
                /*.flatMap(new RichFlatMapFunction<Tuple2<String, Long>, Long>() {
                    private ListState<Long> tempState;
                    //private ValueState<Long> tempCount;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        this.tempState = getRuntimeContext().getListState(new ListStateDescriptor<Long>("tempState", Types.LONG));
                        //this.tempCount = getRuntimeContext().getState(new ValueStateDescriptor<Long>("tempState", Types.LONG));
                    }

                    @Override
                    public void flatMap(Tuple2<String, Long> value, Collector<Long> out) throws Exception {
                        this.tempState.add(value.f1);
                        //this.tempCount.update(tempCount.value() + 1);

                        if (this.tempState.get().spliterator().estimateSize() >= 3) {

                            int tempNumSum = 0;
                            for (Long aLong : this.tempState.get()) {
                                tempNumSum += aLong;
                            }
                            out.collect((long) (tempNumSum / 3));
                            this.tempState.clear();
                        }
                    }
                })*/
                /**
                 * MapState
                 */
                /*.flatMap(new RichFlatMapFunction<Tuple2<String, Long>, Long>() {

                    private MapState<String, Long> tempState;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        this.tempState = getRuntimeContext().getMapState(new MapStateDescriptor<String, Long>("tempState", Types.STRING, Types.LONG));
                    }

                    @Override
                    public void flatMap(Tuple2<String, Long> value, Collector<Long> out) throws Exception {
                        //  UUID 作为Key 避免 HASH 冲突
                        this.tempState.put(UUID.randomUUID().toString(), value.f1);


                        if (this.tempState.keys().spliterator().estimateSize() >= 3) {
                            int tempNumSum = 0;
                            for (Iterator<Map.Entry<String, Long>> it = this.tempState.iterator(); it.hasNext(); ) {
                                tempNumSum += it.next().getValue();
                            }
                            out.collect((long) (tempNumSum / 3));
                            this.tempState.clear();
                        }
                    }
                })*/

                .print();


        try {
            env.execute();
            env.getExecutionPlan();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
