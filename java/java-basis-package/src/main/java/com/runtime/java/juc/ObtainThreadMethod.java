package com.runtime.java.juc;

import java.util.concurrent.*;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/30 9:03
 * @Description
 */
public class ObtainThreadMethod {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1.
        Runnable run1 = () -> {
            System.out.println("第一种 ");
        };
        run1.run();

        //2.
        new t().run();

        //3.
        new Thread(
                new FutureTask<Object>(
                        () -> {
                            System.out.println("第三种 Thread 以 FutureTask (Callable) 作为参数 传递 ");
                            return "success";
                        }
                )).start();

        //4.
        //Executors.newFixedThreadPool(3);
        //Executors.newSingleThreadExecutor();
        //Executors.newCachedThreadPool();
        //ExecutorService executorService = new ThreadPoolExecutor(...)

//        FutureTask<Object> task = new FutureTask<>(
//                () -> {
//                    System.out.println("1111");
//                    return "Success";
//                }
//        );
//
//
//        new Thread(task).start();
//        System.out.println(task.get());


    }
}

class t extends Thread {
    @Override
    public void run() {
        System.out.println("第二种 ");
    }
}
