package yuque;


import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/3/10 17:34
 * @Description
 */
public class MsThreadLocal {
    public static void main(String[] args) {

        ThreadLocal threadLocal = new ThreadLocal();

        WeakHashMap hashMap = new WeakHashMap<>();

        // 软引用
        SoftReference s = new SoftReference<>("");

        // 弱引用
        WeakReference e = new WeakReference<>("");

        // 虚引用
        PhantomReference phantomReference = new PhantomReference<Object>(null, null);

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.writeLock();
        readWriteLock.readLock();

        Map version1 = new Hashtable();
        Map Version2 = new HashMap<>();
        new LinkedHashMap<>().put(null,null);
        Map version3 = Collections.synchronizedMap(null);
        Map version4 = new ConcurrentHashMap<>();

        Queue queueVersion1 = new ConcurrentLinkedQueue<>();

        Map version5 = new ConcurrentSkipListMap();


        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        List<Object> list = Collections.singletonList(null);
        List<Object> list1 = Collections.synchronizedList(null);

        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();

        BlockingQueue blockingQueue = new LinkedBlockingQueue<>();
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(10);

        DelayQueue delayQueue = new DelayQueue<>();
        PriorityQueue priorityQueue = new PriorityQueue();
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue<>();
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue();
        // linkedTransferQueue.transfer();

        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(1);
        Executors.newCachedThreadPool();
        Executors.newWorkStealingPool();

        ForkJoinPool pool = new ForkJoinPool();
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        class e extends RecursiveAction{
            @Override
            protected void compute() {

            }
        }

        Collections.singletonList(null);


    }
}

