package juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/30 9:31
 * @Description
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {


        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            new Thread(
                    () -> {
                        try {
                            System.out.println(Thread.currentThread().getName() + "\t 号同学离开教室");
                            latch.countDown();
                            // TimeUnit.SECONDS.sleep(10);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, String.valueOf(i)
            ).start();
        }
        latch.await(2, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getName() + "离开");

    }
}
