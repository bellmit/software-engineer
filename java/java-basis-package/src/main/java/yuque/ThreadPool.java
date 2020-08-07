package yuque;

import java.util.concurrent.*;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/30 11:06
 * @Description
 */
public class ThreadPool {
    public static void main(String[] args) {

        //************************************************************
        ExecutorService service = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors() + 1, //5,
                2,
                TimeUnit.SECONDS,

                //new ArrayBlockingQueue<>(3),
                //new LinkedBlockingQueue<>(3),
                new SynchronousQueue(),

                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()
                new ThreadPoolExecutor.CallerRunsPolicy()
                //new ThreadPoolExecutor.DiscardPolicy()
                //new ThreadPoolExecutor.DiscardOldestPolicy()
        );


        /***
         * Runnable ---> execute(Runnable command) ：执行任务/命令，没有返回值，一般用来执行
         *
         * Callable ---> submit(Callable<T> task)：执行任务，有返回值，一般又来执行
         *
         *  void shutdown() ：关闭连接池
         */


        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);//1池 N 线程
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(); //1池 1 线程
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool(); //1池 N 线程
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(10);


        try {
            for (int i = 0; i < 20; i++) {

                //newFixedThreadPool
                //singleThreadExecutor
                //newCachedThreadPool
                //newScheduledThreadPool
                service
                        .submit(
                                () -> {
                                    System.out.println(Thread.currentThread().getName() + "\t RunTime Process ***");
                                    return 123;
                                }
                        );


                //System.out.println(future.get());
                        /*.execute(
                                () -> {
                                    System.out.println(Thread.currentThread().getName() + "\t RunTime Process ***");
                                }
                        );*/

                //TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //newFixedThreadPool.shutdown();
            //singleThreadExecutor.shutdown();
            //newCachedThreadPool.shutdown();
            //newScheduledThreadPool.shutdown();
            service.shutdown();
        }

    }


}


