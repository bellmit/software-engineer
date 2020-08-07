import java.util.ArrayDeque;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/30 10:47
 * @Description
 */
public class BlockingDequeDemo {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Object> queue = new ArrayBlockingQueue<>(3);
        queue.add(1);
        queue.add(2);
        // queue.add(3);
        System.out.println(queue.offer(1));
        //System.out.println(queue.offer(2));
        //System.out.println(queue.offer(3));

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());

        TimeUnit.SECONDS.sleep(3);
        queue.offer(333);
        System.out.println(queue.take());

        //System.out.println(queue.contains(1));
        queue.offer(1);
        queue.offer(1);
        queue.offer(1);
        queue.offer(888, 3, TimeUnit.SECONDS);
        System.out.println(queue.take());

    }

}
