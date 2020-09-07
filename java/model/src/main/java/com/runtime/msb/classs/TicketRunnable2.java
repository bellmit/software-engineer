package com.runtime.msb.classs;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/9/5 17:34
 * @Description
 */
public class TicketRunnable2 implements Runnable {

    private int ticket = 5;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {

           // try { Thread.sleep(100); }catch (Exception e){}

            synchronized (this) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "正在出售第" + (ticket--) + "张票");
                }

                try { Thread.sleep(100); }catch (Exception e){}

            }

        }


    }

    public static void main(String[] args) {
        TicketRunnable2 run = new TicketRunnable2();
        Thread t1 = new Thread(run, "A");
        Thread t2 = new Thread(run, "B");
        Thread t3 = new Thread(run, "C");
        Thread t4 = new Thread(run, "D");
        Thread t5 = new Thread(run, "E");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

}
