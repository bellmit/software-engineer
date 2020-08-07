package com.runtime.java.collections.hashmap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/21 15:23
 * @Description
 */
public class HashMapInstance {
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();

        map.put(1,1);
        map.remove(1);





        new ConcurrentHashMap<>();
        //new ConcurrentHashMap.KeySetView<>()
    }
}
