package runtime.jar;

import com.alibaba.fastjson.JSON;
import lombok.*;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import runtime.utils.datasource.CustomKafkaConsumer;

import javax.annotation.Nullable;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/28 10:52
 * @Description
 */
public class softwareFV1 {

    public static void main(String[] args) {

       // StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);


        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        env.getCheckpointConfig().setCheckpointTimeout(10000L);
        env.getCheckpointConfig().setCheckpointInterval(5000L);




        CustomKafkaConsumer.getResultMethod(env, args, "software")
                .map(new softwareFV1MapFunc())
                .assignTimestampsAndWatermarks(new softwareFV1BoundOutOfTime())
                .keyBy(CustomSource::getStateStage)
                .timeWindow(Time.seconds(5))
                .process(new ProcessWindowFunction<CustomSource, Object, String, TimeWindow>() {
                    @Override
                    public void process(String s, Context context, Iterable<CustomSource> elements, Collector<Object> out) throws Exception {
                        CustomSource source = elements.iterator().next();
                        //System.out.println(source);
                        out.collect(source.getStr() + "\t" + System.currentTimeMillis());
                    }
                }).setParallelism(15)
//                .countWindowAll(10)
//                .process(new ProcessAllWindowFunction<Object, Object, GlobalWindow>() {
//                    @Override
//                    public void process(Context context, Iterable<Object> elements, Collector<Object> out) throws Exception {
//                        System.out.println("\n");
//                    }
//                })
                .print();

        try {
            env.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Custom assigner
     */
    public static class softwareFV1BoundOutOfTime implements AssignerWithPeriodicWatermarks<CustomSource> {

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
    public static class softwareFV1MapFunc implements MapFunction<String, CustomSource> {
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
