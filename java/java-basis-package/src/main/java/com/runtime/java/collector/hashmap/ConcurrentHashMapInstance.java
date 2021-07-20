package com.runtime.java.collector.hashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/21 15:23
 * @Description
 */
public class ConcurrentHashMapInstance {
    static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
    static final int NCPU = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {


        System.out.println(Runtime.getRuntime().availableProcessors());

        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();

        map.put("1", 1);
        map.get("1");
        map.remove("1");


    }

    static final int spread(int h) {
        return (h ^ (h >>> 16)) & HASH_BITS;
    }

}
