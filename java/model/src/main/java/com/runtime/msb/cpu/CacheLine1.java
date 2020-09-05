package com.runtime.msb.cpu;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/9/5 10:28
 * @Description
 */
public class CacheLine1 {
    public static volatile long[] arr = new long[2];

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 1_0000_0000L; i++) {
                arr[1] = i;
            }
        });


        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 1_0000_0000L; i++) {
                arr[1] = i;
            }
        });

        long start = System.nanoTime();

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start) / 100_0000);
    }

}
