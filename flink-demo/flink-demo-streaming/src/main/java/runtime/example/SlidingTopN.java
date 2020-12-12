package runtime.example;

import lombok.*;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import runtime.pojo.Version1Pojo;
import runtime.utils.datasource.CustomKafkaConsumer;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/9 20:10
 * @Description
 */
public class SlidingTopN {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        DataStream<Version1Pojo> stream = CustomKafkaConsumer.getResultMethod(env, args, "T1", "Version1Pojo");


        stream
                .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<Version1Pojo>(Time.seconds(5)) {
                    @Override
                    public long extractTimestamp(Version1Pojo element) {
                        return element.getTimestamp();
                    }
                })
                .keyBy(Version1Pojo::getUserId)
                .window(SlidingEventTimeWindows.of(Time.minutes(10), Time.seconds(5)))
                .aggregate(null);
                //.keyBy()



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
    class ViewPagePojo {
        private String ipAddress;

    }

}
