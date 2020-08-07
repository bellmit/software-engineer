package com.runtime.java.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/29 15:10
 * @Description
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {


        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙");
        });

        for (int i = 0; i < 7; i++) {
            int finalI = i;
            new Thread(
                    () -> {
                        System.out.println("第 " + finalI + "颗龙珠");
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                    , String.valueOf(i)
            ).start();

        }


    }
}
