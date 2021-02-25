package runtime.utils;

import org.apache.flink.streaming.api.windowing.time.Time;

import java.text.SimpleDateFormat;
import java.util.Date;

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


    public static String get_YYYY_MM_DD() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }
    public static String GET_YYYY_MM_DD_HH_MM_SS() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static String GET_YYYY_MM_DD_HH_MM_SS_SSS() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return df.format(new Date());
    }
}
