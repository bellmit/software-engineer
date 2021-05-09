package com.runtime.java.collections.hashmap;


import java.util.*;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/21 15:23
 * @Description
 */
public class HashMapInstance {
    public static void main(String[] args) {

        // 循环遍历Map的 N 中方法
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1, 2);

        // 1. entrySet遍历，在键和值都需要时使用（最常用）
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
        // 2. 通过keySet或values来实现遍历,性能略低于第一种方式
        // 遍历map中的键
        for (Integer key : map.keySet()) {
            System.out.println("key = " + key);
        }
        // 遍历map中的值
        for (Integer value : map.values()) {
            System.out.println("value = " + value);
        }
        // 3. 使用Iterator遍历
        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }

        // 另一种  Iterator 方式
        for (Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<Integer, Integer> tempNext = iterator.next();
            System.out.println(tempNext.getKey() + " A " + tempNext.getValue());
        }

        // 4. java8 Lambda
        // java8提供了Lambda表达式支持，语法看起来更简洁，可以同时拿到key和value，
        // 不过，经测试，性能低于entrySet,所以更推荐用entrySet的方式
        map.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });


        List<Map.Entry<String, LinkedList<Object>>> list = null;

        //new ConcurrentHashMap.KeySetView<>()


        Hashtable hashtable = new Hashtable();
        /**
         *     // Make sure the value is not null
         *         if (value == null) {
         *             throw new NullPointerException();
         *         }
         */
        //hashtable.put()


    }


    /**
     * HashMap
     * 1.7 -> 数组 + 链表
     * 1.8 -> 数组 + 链表 + 红黑树
     * <p>
     * <p>
     * 数组 + 链表 +
     */


}
