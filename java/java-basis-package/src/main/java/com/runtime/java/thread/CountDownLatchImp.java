package com.runtime.java.thread;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/5 10:58
 * @Description
 */
public class CountDownLatchImp {

    static volatile int NUM = 10;

    public static void main(String[] args) throws Exception {

        CountDownLatch latch = new CountDownLatch(1);



        while (NUM != 0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please Input Number ? ");
            if ("countDownLatch".equals(scanner.nextLine())) {
                latch.countDown();
                break;
            }
            --NUM;
        }

        //latch.countDown();
        latch.await();


        System.out.println("123");
    }

}



