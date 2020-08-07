package com.runtime.java.thread;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/5 18:17
 * @Description
 */
public class ConsumerProduct {
    public static void main(String[] args) {

        Tail tail = new Tail();

        new Thread(
                () -> {

                    for (int i = 0; i < 10; i++) {

                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            tail.get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }, "A"
        ).start();


        new Thread(
                () -> {

                    for (int i = 0; i < 10; i++) {

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            tail.set();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }, "B"
        ).start();

        new Thread(
                () -> {

                    for (int i = 0; i < 10; i++) {

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            tail.get();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }, "AA"
        ).start();


        new Thread(
                () -> {

                    for (int i = 0; i < 10; i++) {

                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            tail.set();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }, "BB"
        ).start();


    }
}


class Tail {
    int num = 0;

    // 判断 干活 通知
    public synchronized void get() throws Exception {

        //if (num != 0) {
        while (num != 0) {
            //System.out.println(Thread.currentThread().getName());
            this.wait();
        }

        System.out.println("Get () \t" + (++num));

        this.notifyAll();
    }


    public synchronized void set() throws Exception {
        //if (num == 0) {
        while (num == 0) {
            this.wait();
        }

        System.out.println("Set () \t" + (--num));

        this.notifyAll();
    }
}