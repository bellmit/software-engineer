package juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/30 9:48
 * @Description
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {


        CyclicBarrier barrier = new CyclicBarrier(7, () -> { System.out.println("happy "); });

        for (int i = 0; i < 7; i++) {
            new Thread(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + "被 收集");
                        try {
                            barrier.await();
                            //System.out.println("关闭");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }

                    },String.valueOf(i)
            ).start();
        }


    }
}
