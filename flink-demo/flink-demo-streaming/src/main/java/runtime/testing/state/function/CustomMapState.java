package runtime.testing.state.function;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.MapState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.typeinfo.IntegerTypeInfo;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

import java.util.UUID;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/18 20:30
 * @Description
 */
public class CustomMapState extends RichFlatMapFunction<Tuple2<String, Long>, Tuple2<String, Long>> {

    private MapState<String, Integer> mapState;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

        MapStateDescriptor<String, Integer> descriptor = new MapStateDescriptor<>(
                "mapState", Types.STRING, Types.INT);
        this.mapState = getRuntimeContext().getMapState(descriptor);

    }

    @Override
    public void flatMap(Tuple2<String, Long> value, Collector<Tuple2<String, Long>> out) throws Exception {

        System.out.println(mapState.keys());
        System.out.println(mapState.keys().spliterator().estimateSize());

        if (mapState.keys().spliterator().estimateSize() > 3) {
            System.out.println("three access");
            mapState.clear();
        }
        this.mapState.put(UUID.randomUUID().toString(), 0);

    }

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
}
