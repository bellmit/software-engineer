package runtime.window;

import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.DynamicProcessingTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import runtime.pojo.Version1Pojo;
import runtime.utils.CustomKafkaConsumer;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/26 14:23
 * @Description
 */
public class CustomWindow {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<Version1Pojo> stream = CustomKafkaConsumer.getResultMethod(env, args, "T1", "Version1Pojo");
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);

        /**
         * 验证 Flink Window 的 Count Window 以及原理实现 [TumblingCountWindow,Sli]
         */

        // countWindow  滑动窗口与滚动窗口
        stream.keyBy(Version1Pojo::getUserId)
                //.countWindow(1)
                .countWindow(4, 5) // 预期 输出触发一次 为 6
                .process(new ProcessWindowFunction<Version1Pojo, Object, Integer, GlobalWindow>() {
                    @Override
                    public void process(Integer integer, Context context, Iterable<Version1Pojo> elements, Collector<Object> out) throws Exception {

                        for (Version1Pojo element : elements) {
                            System.out.println(element);
                        }
                        System.out.println("触发一次");
                        Thread.sleep(1000);
                    }
                });


        // timeWindow
        stream.keyBy(Version1Pojo::getUserId)
                //.timeWindow(Time.seconds(10))
                .timeWindow(Time.seconds(1), Time.seconds(1))
                .process(new ProcessWindowFunction<Version1Pojo, Object, Integer, TimeWindow>() {
                    @Override
                    public void process(Integer integer, Context context, Iterable<Version1Pojo> elements, Collector<Object> out) throws Exception {

                        for (Version1Pojo element : elements) {
                            System.out.println(element);
                        }
                        System.out.println("触发一次");
                    }
                });


        // window
        stream.assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<Version1Pojo>(Time.seconds(1)) {
            @Override
            public long extractTimestamp(Version1Pojo element) {
                return element.getTimestamp();
            }
        })
                .keyBy(Version1Pojo::getUserId)
                .window(TumblingEventTimeWindows.of(Time.seconds(1)))
                //.window(TumblingProcessingTimeWindows.of(Time.seconds(1)))
                //.window(SlidingEventTimeWindows.of(Time.seconds(1), Time.seconds(1)))
                //.window(SlidingProcessingTimeWindows.of(Time.seconds(1), Time.seconds(1)))
                //.window(EventTimeSessionWindows.withGap(Time.seconds(1)))
                //.window(ProcessingTimeSessionWindows.withGap(Time.seconds(1)))
                //.window(GlobalWindows.create())
                //.window(DynamicProcessingTimeSessionWindows.withDynamicGap(null))
                //.window(DynamicEventTimeSessionWindows.withDynamicGap(null))
                .process(new ProcessWindowFunction<Version1Pojo, Object, Integer, TimeWindow>() {
                    @Override
                    public void process(Integer integer, Context context, Iterable<Version1Pojo> elements, Collector<Object> out) throws Exception {

                        for (Version1Pojo element : elements) {
                            System.out.println(element);
                        }
                        System.out.println("触发一次");
                    }
                });


        // windowAll
        stream.keyBy(Version1Pojo::getUserId)
//                .windowAll(TumblingProcessingTimeWindows.of(Time.seconds(1)))
//                .windowAll(TumblingEventTimeWindows.of(Time.seconds(1)))
//                .windowAll(SlidingProcessingTimeWindows.of(Time.seconds(1),Time.seconds(1)))
//                .windowAll(SlidingEventTimeWindows.of(Time.seconds(1),Time.seconds(1)))
//                .windowAll(DynamicProcessingTimeSessionWindows.withDynamicGap(null))
                .windowAll(DynamicProcessingTimeSessionWindows.withDynamicGap(null))
//                .window(GlobalWindows.create())
                //.trigger(new CountTrigger<>(1))
                .process(new ProcessAllWindowFunction<Version1Pojo, Object, TimeWindow>() {
                    @Override
                    public void process(Context context, Iterable<Version1Pojo> elements, Collector<Object> out) throws Exception {

                        for (Version1Pojo element : elements) {
                            System.out.println(element);
                        }
                        System.out.println("触发一次");
                    }
                });


        // Non-KeyBy AllWindow
        stream.windowAll(TumblingEventTimeWindows.of(Time.seconds(1)));
        stream.timeWindowAll(Time.seconds(1));

        env.execute();
    }
}
