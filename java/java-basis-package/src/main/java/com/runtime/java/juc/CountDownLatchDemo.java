package com.runtime.java.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/29 15:00
 * @Description
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch downLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {

            new Thread(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + "\t离开教室");
                        downLatch.countDown();
                    }, String.valueOf(i)
            ).start();
        }
        downLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t班长走人");


    }

    private static void closeDoor() {
        for (int i = 0; i < 6; i++) {
            new Thread(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + "\t离开教室");
                    }, String.valueOf(i)
            ).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t" + "班长走人");
    }
}
