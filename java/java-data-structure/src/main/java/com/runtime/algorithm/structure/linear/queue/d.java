package com.runtime.algorithm.structure.linear.queue;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/10 16:41
 * @Description
 */
public class d {
    public static void main(String[] args) {

        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();
        queue.add("A");
        queue.add("AA");
        System.out.println(Arrays.toString(queue.toArray()));

        int[] arr = {1};
        Array.newInstance(Object.class,1);

    }
}
