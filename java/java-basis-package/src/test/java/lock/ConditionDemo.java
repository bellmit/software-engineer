package lock; /**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/29 10:38
 * @Description
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程 操作A打印5次 b打印10次 c打印15次 依次打印
 */

public class ConditionDemo {
    public static void main(String[] args) {
        Conditions1 con = new Conditions1();


        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        con.print1();
                    }
                },"A"
        ).start();
        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        con.print2();
                    }
                },"B"
        ).start();
        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        con.print3();
                    }
                },"C"
        ).start();



    }
}


class Conditions1 {
    private int number = 0;
    final Lock lock = new ReentrantLock();
    final Condition c1 = lock.newCondition();
    final Condition c2 = lock.newCondition();
    final Condition c3 = lock.newCondition();

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

    public void print2() {
        lock.lock();
        try {
            while (number != 1) {
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
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

    public void print3() {
        lock.lock();
        try {
            while (number != 2) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
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
