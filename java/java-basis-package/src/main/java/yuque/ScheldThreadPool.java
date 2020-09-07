package yuque;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/9/7 11:13
 * @Description
 */
public class ScheldThreadPool {
    private static int count = 0;

    public static void main(String[] args) {


        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);

        //executor.schedule(() -> { System.out.println("1"); }, 1, TimeUnit.SECONDS);



        executor.scheduleAtFixedRate(() -> {
            System.out.println("123" + count++);

            if (count == 3) {
                executor.shutdown();
            }

        }, 1, 3, TimeUnit.SECONDS);

    }


}
