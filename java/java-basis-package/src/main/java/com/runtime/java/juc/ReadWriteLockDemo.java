package com.runtime.java.juc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/29 15:55
 * @Description
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {


        //new ArrayBlockingQueue<>(3);

        MyCache myCache = new MyCache();

        for (int i = 0; i < 6; i++) {
            int implInt = i;
            new Thread(
                    () -> {
                        myCache.put("" + implInt, implInt + "");
                    }, String.valueOf(i)
            ).start();
        }

        for (int i = 0; i < 6; i++) {
            int implInt = i;
            new Thread(
                    () -> {
                        myCache.get("" + implInt);
                    }, String.valueOf(i)
            ).start();
        }

    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    //Lock lock = new ReentrantLock();
    private ReadWriteLock lock = new ReentrantReadWriteLock();


    public void put(String key, String value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 写入操作");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }

    }


    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "-----> \t 读开始");
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "-----> \t 读结束" + "\t " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
            //lock.unlock();
        }


        ;
    }


}