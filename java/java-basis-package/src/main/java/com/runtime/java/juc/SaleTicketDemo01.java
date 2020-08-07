package com.runtime.java.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/28 9:50
 * @Description  线程 --> 操作 --> 资源类
 */

class Ticket {
    private int number = 30;

    Lock lock = new ReentrantLock();

    public void sale() {

        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第:" + (number--) + "张票,\t还剩" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

public class SaleTicketDemo01 {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();


        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.sale(); },"A").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.sale(); },"B").start();;
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket.sale(); },"C").start();;


       new Thread(() -> {
           for (int i = 0; i < 40; i++) {
               ticket.sale();
           }
       }, "A").start();
    }
}
