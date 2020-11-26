package runtime.join;

import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/16 19:10
 * @Description
 */
public class Join {
    public static void main(String[] args) {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Object> source1 = env.addSource(null);
        DataStreamSource<Object> source2 = env.addSource(null);


        source1
                .join(source2)
                .where(value -> value)
                .equalTo(value -> value)
                .window(TumblingEventTimeWindows.of(Time.seconds(5)))
                .apply(
                        (JoinFunction<Object, Object, Object>) (first, second) -> null);

    }

}
