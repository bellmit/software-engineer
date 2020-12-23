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
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import runtime.utils.datasource.CustomKafkaConsumer;

import javax.annotation.Nullable;
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
                .map(new HourWindowOperationMapFunc())
                .assignTimestampsAndWatermarks(new HourWindowOperationBoundOutOfTime())
                .keyBy(CustomSource::getStateStage)
                .timeWindow(Time.seconds(5))
                .aggregate(new HourWindowOperationAggFunc(), new HourWindowOperationTimeWindow())
                .keyBy("key", "timeStamp")
                .process(new HourWindowOperationKeyedProcessFunc())
                .print();


        try {
            System.out.println(env.getExecutionPlan());
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Custom ProcessFunc
     */
    public static class HourWindowOperationKeyedProcessFunc extends KeyedProcessFunction<Tuple, CustomWindowPojo, Long> {

        /**
         * List State
         */
        transient ListState<CustomWindowPojo> listState;

        @Override
        public void open(Configuration parameters) throws Exception {
            super.open(parameters);
            ListStateDescriptor<CustomWindowPojo> descriptor = new ListStateDescriptor<>("listState", CustomWindowPojo.class);
            this.listState = getRuntimeContext().getListState(descriptor);
        }

        @Override
        public void processElement(CustomWindowPojo value, Context ctx, Collector<Long> out) throws Exception {
            this.listState.add(value);
            ctx.timerService().registerEventTimeTimer(System.currentTimeMillis() - (System.currentTimeMillis() % 3600000) + 1);
            //ctx.timerService().registerEventTimeTimer(System.currentTimeMillis() + 10000);
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

    }

    /**
     * Custom agg
     */
    public static class HourWindowOperationAggFunc implements AggregateFunction<CustomSource, Long, Long> {
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

    }

    /**
     * Custom Window
     */
    public static class HourWindowOperationTimeWindow implements WindowFunction<Long, CustomWindowPojo, String, TimeWindow> {
        @Override
        public void apply(String s, TimeWindow window, Iterable<Long> input, Collector<CustomWindowPojo> out) throws
                Exception {
            out.collect(new CustomWindowPojo(s, input.iterator().next(), window.getEnd()));
        }
    }

    /**
     * Custom assigner
     */
    public static class HourWindowOperationBoundOutOfTime implements AssignerWithPeriodicWatermarks<CustomSource> {

        Long currentMaxEventTime = 0L;//设置当前窗口里面最大的时间
        int maxOufOfOrderTime = 10000;//最大乱序时间 10s

        @Nullable
        @Override
        public Watermark getCurrentWatermark() {
            return new Watermark(currentMaxEventTime - maxOufOfOrderTime);
        }

        @Override
        public long extractTimestamp(CustomSource element, long previousElementTimestamp) {
            Long eventTime = element.getTimeStamp();
            currentMaxEventTime = Math.max(eventTime, currentMaxEventTime);
            return eventTime;
        }

    }

    /**
     * Custom Map
     */
    public static class HourWindowOperationMapFunc implements MapFunction<String, CustomSource> {
        @Override
        public CustomSource map(String value) throws Exception {
            return JSON.parseObject(value, CustomSource.class);
        }
    }

    /**
     * 元数据
     */
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

    /**
     * 自定义封装bean
     */
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
