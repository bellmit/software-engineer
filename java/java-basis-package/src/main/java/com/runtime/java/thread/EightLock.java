package com.runtime.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/7 13:43
 * @Description
 */
public class EightLock {

    public static void main(String[] args) {
        Number number = new Number();

        new Thread(
                () -> {
                    //new Number().numberOne();
                    Number.numberOne();
                }, "??"
        ).start();

        new Thread(
                () -> {
                    //new Number().numberTwo();
                    number.numberTwo();
                }, "??"
        ).start();

    }

}

class Number {

    public static synchronized /*synchronized*/ void numberOne()  {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("numberOne");
    }

    public /*synchronized*/ void numberTwo() {
        System.out.println("numberTwo");
    }

}