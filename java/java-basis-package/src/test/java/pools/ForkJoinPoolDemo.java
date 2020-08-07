package pools;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/30 17:08
 * @Description
 */
public class ForkJoinPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        demo demo1 = new demo(0, 10000);
        System.out.println(new ForkJoinPool(1).submit(demo1).get());


        demo demo = new demo(0, 1000);
        ForkJoinTask<Integer> task = new ForkJoinPool(1).submit(demo);
        System.out.println(task.get());

    }
}

class demo extends RecursiveTask<Integer> {

    private static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public demo(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - begin < ADJUST_VALUE) {
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {
            int middle = (begin + end) / 2;
            demo demo1 = new demo(begin, middle);
            demo demo = new demo(middle + 1, end);

            demo.fork();
            demo1.fork();
            return demo.join() + demo1.join();
        }
        return result;
    }
}
