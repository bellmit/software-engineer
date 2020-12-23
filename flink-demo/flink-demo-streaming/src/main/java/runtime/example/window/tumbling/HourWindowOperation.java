package runtime.example.window.tumbling;


import com.alibaba.fastjson.JSON;
import lombok.*;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import runtime.utils.datasource.CustomKafkaConsumer;

import java.util.ArrayList;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/23 14:28
 * @Description
 */
public class HourWindowOperation {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);


        CustomKafkaConsumer.getResultMethod(env, args, "software")
                .map(new MapFunction<String, CustomSource>() {
                    @Override
                    public CustomSource map(String value) throws Exception {
                        return JSON.parseObject(value, CustomSource.class);
                    }
                }).assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<CustomSource>(Time.seconds(2)) {
            @Override
            public long extractTimestamp(CustomSource element) {
                return element.timeStamp;
            }
        })
                .keyBy(CustomSource::getStateStage)
                .timeWindow(Time.seconds(5))
                .aggregate(new AggregateFunction<CustomSource, Long, Long>() {
                    @Override
                    public Long createAccumulator() {
                        return 0L;
                    }

                    @Override
                    public Long add(CustomSource value, Long accumulator) {
                        return accumulator + 1L;
                    }

                    @Override
                    public Long getResult(Long accumulator) {
                        return accumulator;
                    }

                    @Override
                    public Long merge(Long a, Long b) {
                        return a + b;
                    }
                }, new WindowFunction<Long, CustomWindowPojo, String, TimeWindow>() {
                    @Override
                    public void apply(String s, TimeWindow window, Iterable<Long> input, Collector<CustomWindowPojo> out) throws Exception {
                        out.collect(new CustomWindowPojo(s, input.iterator().next(), window.getEnd()));
                    }
                }).keyBy("key", "timeStamp")
                .process(new KeyedProcessFunction<Tuple, CustomWindowPojo, Long>() {

                    ListState<CustomWindowPojo> listState;

                    @Override
                    public void open(Configuration parameters) throws Exception {
                        super.open(parameters);
                        ListStateDescriptor<CustomWindowPojo> descriptor = new ListStateDescriptor<>("listState", CustomWindowPojo.class);
                        this.listState = getRuntimeContext().getListState(descriptor);
                    }

                    @Override
                    public void processElement(CustomWindowPojo value, Context ctx, Collector<Long> out) throws Exception {
                        this.listState.add(value);
                        // ctx.timerService().registerEventTimeTimer(System.currentTimeMillis() - System.currentTimeMillis() % 3600000);
                        ctx.timerService().registerEventTimeTimer(System.currentTimeMillis() + 10000);
                    }

                    @Override
                    public void onTimer(long timestamp, OnTimerContext ctx, Collector<Long> out) throws Exception {
                        System.out.println("当前key: " + ctx.getCurrentKey() + "\t" + "本次计算时间:" + timestamp);
                        super.onTimer(timestamp, ctx, out);
                        ArrayList<CustomWindowPojo> list = new ArrayList<>();

                        for (CustomWindowPojo everyState : this.listState.get()) {
                            list.add(everyState);
                        }
                        this.listState.clear();


                        Long count = 0L;
                        for (CustomWindowPojo pojo : list) {
                            count += pojo.getCountNum();
                        }
                        out.collect(count);
                    }
                }).print();


        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class CustomSource {
        private String str;
        private String StateStage;
        private Integer money;
        private Long timeStamp;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class CustomWindowPojo {
        private String key;
        private Long CountNum;
        private Long timeStamp;
    }

}
