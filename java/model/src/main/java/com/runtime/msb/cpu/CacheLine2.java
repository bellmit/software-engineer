package com.runtime.msb.cpu;

import sun.misc.Contended;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/9/5 10:28
 * @Description
 */
public class CacheLine2 {
    public static volatile long[] arr = new long[16];
    //@Contended  private int a;


    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(()->{
            for (long i = 0; i < 1_0000_0000L; i++) {
                arr[0] = i;
            }
        });
        Thread t2 = new Thread(()->{
            for (long i = 0; i < 1_0000_0000L; i++) {
                arr[8] = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start)/100_0000);
    }
}
