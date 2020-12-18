package runtime.testing.state.function.merging;

import com.google.common.collect.Lists;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/18 20:30
 * @Description
 */
public class CustomListState extends RichFlatMapFunction<Tuple2<String, Long>, Tuple2<String, Long>> {

    private ListState<Tuple2<String, Long>> listState;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

        ListStateDescriptor<Tuple2<String, Long>> descriptor = new ListStateDescriptor<>(
                //"listState", Types.TUPLE(Types.STRING, Types.LONG));
                "listState", TypeInformation.of(new TypeHint<Tuple2<String, Long>>() {
        }));

        this.listState = getRuntimeContext().getListState(descriptor);


    }

    @Override
    public void flatMap(Tuple2<String, Long> value, Collector<Tuple2<String, Long>> out) throws Exception {

        Iterable<Tuple2<String, Long>> tempState = listState.get();
        if (tempState == null) {
            this.listState.addAll(Collections.emptyList());
        }

        this.listState.add(value);

        long size = this.listState.get().spliterator().estimateSize();
        if (size > 3) {
            System.out.println("three access");
            this.listState.clear();
        }

        //Lists.newArrayList(this.listState.get()).size();


    }

    /*.flatMap(new RichFlatMapFunction<Tuple2<String, Long>, Long>() {
                    private ListState<Long> tempState;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        this.tempState = getRuntimeContext().getListState(new ListStateDescriptor<Long>("tempState", Types.LONG));
                    }

                    @Override
                    public void flatMap(Tuple2<String, Long> value, Collector<Long> out) throws Exception {
                        this.tempState.add(value.f1);

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


}
