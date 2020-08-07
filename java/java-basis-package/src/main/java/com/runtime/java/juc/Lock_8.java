package com.runtime.java.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/28 17:42
 * @Description
 */
public class Lock_8 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone1 = new Phone();

        Lock lock = new ReentrantLock();
        lock.lock();

        new Thread(
                () -> {
                    try {
                        //Phone.sendEmail();
                        phone.sendEmail();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }, "??"
        ).start();


        new Thread(
                () -> {
                    try {

                        //phone.sendSMS();
                        phone1.sendSMS();
                        //phone.getHello();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }, "??"
        ).start();

    }
}

class Phone {

    public static synchronized void sendSMS() throws Exception {

        System.out.println("------sendSMS");
    }

    public static synchronized void sendEmail() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }

}