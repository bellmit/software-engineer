package com.runtime.java.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/7 9:35
 * @Description
 */
public class ThreadComm {
    public static void main(String[] args) {

        // 线程 : 线程 操作 资源类
        // 通讯 : 判断 工作 通知

        ResourceMethod method = new ResourceMethod();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                method.method1(1);
            }, "A").start();

            new Thread(() -> {
                method.method2(2);
            }, "B").start();

            new Thread(() -> {
                method.method3(3);
                System.out.println("--------------------");
            }, "C").start();

            //Thread.yield();
        }

    }
}

class ResourceMethod {
    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int num = 1;

    public void method1(int count) {
        lock.lock();
        try {
            if (num != 1) condition1.await();

            for (int i = 0; i < count; i++) System.out.println("线程 " + Thread.currentThread().getName() + "\t " + i);

            num = 2;

            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void method2(int count) {
        lock.lock();
        try {
            if (num != 2) condition2.await();

            for (int i = 0; i < count; i++) System.out.println("线程 " + Thread.currentThread().getName() + "\t " + i);


            num = 3;
            condition3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void method3(int count) {
        lock.lock();
        try {

            if (num != 3) condition3.await();

            for (int i = 0; i < count; i++) System.out.println("线程 " + Thread.currentThread().getName() + "\t " + i);


            num = 1;

            condition1.signal();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}

