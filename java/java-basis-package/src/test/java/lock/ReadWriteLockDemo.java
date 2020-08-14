package lock;

import java.time.temporal.ValueRange;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/30 10:13
 * @Description
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    //private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Lock rwLock = new ReentrantLock();

    public void put(String key, Object value) {
        //rwLock.writeLock().lock();
       // rwLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写" + key);
            //暂停一会儿线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写完了" + key);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //rwLock.writeLock().unlock();
            //rwLock.unlock();
        }

    }

    public Object get(String key) {
        //rwLock.readLock().lock();
       // rwLock.lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读完了" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //rwLock.readLock().unlock();
         //   rwLock.unlock();
        }
        return result;
    }
}

public class ReadWriteLockDemo {


    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.put(num + "", num + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(() -> {
                myCache.get(num + "");
            }, String.valueOf(i)).start();
        }


        TimeUnit.SECONDS.sleep(5);
        //System.out.println(123);
        ;

    }


    //Lock lock = new ReentrantLock();
    //Lock lock;
    //Condition condition = lock.newCondition();


}