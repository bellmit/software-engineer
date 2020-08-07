package juc;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/30 10:04
 * @Description
 */
public class SemaphoreDemo {
    public static void main(String[] args) {


        Semaphore semaphore = new Semaphore(3);

        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            new Thread(
                    () -> {

                        try {
                            semaphore.acquire(); //从池获取 资源 并阻塞直到可用
                            System.out.println(Thread.currentThread().getName() + "\t 抢到了 @@");
                            TimeUnit.SECONDS.sleep(random.nextInt(10));
                            System.out.println(Thread.currentThread().getName() + "\t 释放 ---");
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            semaphore.release();//释放资源,返回池
                        }

                    }, String.valueOf(i)
            ).start();
        }


    }
}
