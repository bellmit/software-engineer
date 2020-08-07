package com.runtime.java.juc;

import java.nio.IntBuffer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/29 10:10
 * @Description
 */
public class ConditionDemo {
    public static void main(String[] args) {
        //线程 操作 资源类

        Conditions conditions = new Conditions();

        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        conditions.method();
                    }
                }, "A").start();
        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        conditions.method();
                    }
                }, "B").start();
        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        conditions.method();
                    }
                }, "C").start();
    }
}

class Conditions {
    // 标识
    private int number = 0;

    final Lock lock = new ReentrantLock();
    final Condition c3 = lock.newCondition();
    final Condition c2 = lock.newCondition();
    final Condition c1 = lock.newCondition();


    public void method() {

//        lock.lock();
//        try {
        String name = Thread.currentThread().getName();

        if ("A".equals(name)) {
            this.print1();
        } else if ("B".equals(name)) {
            this.print2();
        } else {
            this.print3();
        }
//        } catch (Exception e) {
//        } finally {
//            lock.unlock();
//        }

    }

    /*public void print5() {

        lock.lock();
        try {
            while (number != 0) {
                c1.await();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            number = 1;
            c2.signal();
            //*************************************************
            while (number != 1) {
                c2.await();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            number = 2;
            c3.signal();
            //*************************************************
            while (number != 2) {
                c3.await();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            number = 0;
            c1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }*/

    public void print1() {
        lock.lock();
        try {
            while (number != 0) {
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + number++);
            }
            number = 1;
            c2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print3() {
        lock.lock();
        try {
            while (number != 1) {
                c2.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + number++);
            }
            number = 2;
            c3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print2() {
        lock.lock();
        try {
            while (number != 2) {
                c3.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + number++);
            }
            number = 0;
            c1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

