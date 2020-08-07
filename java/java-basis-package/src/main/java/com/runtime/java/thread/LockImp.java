package com.runtime.java.thread;


/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/5 16:31
 * @Description
 */
public class LockImp {
    public static void main(String[] args) {

        Clerk clerk = new Clerk();

        new Thread(
                () -> {

                    for (int i = 0; i < 10; i++) {
                        try {
                            clerk.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "A"
        ).start();


        new Thread(
                () -> {

                    for (int i = 0; i < 10; i++) {
                        try {
                            clerk.sale();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
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
                            clerk.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, "AA"
        ).start();


        new Thread(
                () -> {

                    for (int i = 0; i < 10; i++) {
                        try {
                            clerk.sale();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, "BB"
        ).start();



    }


}

class Clerk {
    private int product = 0;

    public synchronized void get() throws InterruptedException {
        if (product >= 1) {
            Thread.sleep(200);
            System.out.println("产品已满 ");

            this.wait();
        } //else {
            System.out.println(Thread.currentThread().getName() + ":" + ++product);
            this.notifyAll();
        //}


    }


    public synchronized void sale() throws Exception {

        if (product <= 0) {
            System.out.println("缺货 ");
            this.wait();
        } //else {
            System.out.println(Thread.currentThread().getName() + ":" + --product);
            this.notifyAll();
        //}
    }

}