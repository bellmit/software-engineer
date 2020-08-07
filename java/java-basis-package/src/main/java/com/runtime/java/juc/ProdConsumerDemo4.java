package com.runtime.java.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/29 8:52
 * @Description
 */
public class ProdConsumerDemo4 {
    public static void main(String[] args) {
        Airconfition airconfition = new Airconfition();

        for (int i = 0; i < 10; i++) {
            new Thread(
                    () -> {
                        try {
                            airconfition.increment();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, "A"
            ).start();
            new Thread(
                    () -> {
                        try {
                            airconfition.decrement();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }, "B"
            ).start();


            new Thread(
                    () -> {
                        try {
                            airconfition.increment();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, "C"
            ).start();
            new Thread(
                    () -> {
                        try {
                            airconfition.decrement();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }, "D"
            ).start();

        }


    }
}

class Airconfition {

    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();

    //final Lock lock = new ReentrantLock();
    //final Condition notFull = lock.newCondition();
    private int number = 0;

    public void increment() {
        lock.lock();
        try {
            while (number != 0) {
                notFull.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            notFull.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                notFull.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            notFull.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }






/*
    public synchronized void increment() throws InterruptedException {
        //判断 //干活 //通知
        while (number != 0) {
            this.wait();
        }

        number++;

        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }


    public synchronized void decrement() throws Exception {
        while (number == 0) {
            System.out.println("进来一次");
            this.wait();
        }

        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);

        //this.notifyAll();
        this.notifyAll();
    }*/
}
