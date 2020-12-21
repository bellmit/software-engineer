import lombok.val;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/12/20 16:28
 * @Description
 */
public class demo {
    public static void main(String[] args) throws ParseException, InterruptedException {
        //83.149.9.123 - - 17/05/2020:10:05:03 +0000 GET /presentations/logstash-kafkamonitor-2020/images/kibana-search.png
        String str = "83.149.9.123 - - 17/05/2020:10:05:03 +0000 GET /presentations/logstash-kafkamonitor-2020/images/kibana-search.png";
        val dateFormat = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");

        System.out.println(dateFormat.parse(str.split(" ")[3]).getTime());

        System.out.println(tomorrowZeroTimestampMs(System.currentTimeMillis(), 8));
        Thread.sleep(1000);
        System.out.println(tomorrowZeroTimestampMs(System.currentTimeMillis(), 8));
    }


    public static long tomorrowZeroTimestampMs(long now, int timeZone) {
        return now - (now + timeZone * 3600000) % 86400000 + 86400000;
    }
}
