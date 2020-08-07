package pools;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/30 9:23
 * @Description
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Object> task1 = new FutureTask<>(

                () -> {
                    System.out.println("开始表演1 ");
                    TimeUnit.SECONDS.sleep(5);
                    return 1234;
                }
        );

        FutureTask<Object> task2 = new FutureTask<>(

                () -> {
                    System.out.println("开始表演2 ");
                    //TimeUnit.SECONDS.sleep(5);
                    return 2345;
                }
        );




        new Thread(task1, "A").start();
        new Thread(task2, "B").start();//Integer integer = (Integer) task1.get();
        Integer aaa = (Integer) task2.get() + (Integer) task1.get();
        System.out.println(aaa);


    }
}
