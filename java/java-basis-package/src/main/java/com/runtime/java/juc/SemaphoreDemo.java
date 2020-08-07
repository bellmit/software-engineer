package com.runtime.java.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/29 15:30
 * @Description
 */
public class SemaphoreDemo {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i < 6; i++) {
            int tempImp = i;
            new Thread(
                    () -> {
                        try {
                            semaphore.acquire();
                            System.out.println(Thread.currentThread().getName() + "\t ==> 抢到了车位");
                            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                            System.out.println(Thread.currentThread().getName() + "\t------- 离开");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            semaphore.release();
                        }
                    }, String.valueOf(i)
            ).start();
        }

    }
}
