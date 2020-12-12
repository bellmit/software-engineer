package runtime.utils;

import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/26 11:11
 * @Description
 */
public class TimeUtil {
    public static void outputTimeByCountAndOffset(int count, Time offset) {
        long now = System.currentTimeMillis();
        System.out.println("now time : " + now);
        long offsetSeconds = offset.toMilliseconds();
        for (int i = 1; i <= count; i++) {
            System.out.println(now + offsetSeconds * i);
        }
    }
}
