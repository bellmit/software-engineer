package com.runtime.java.collections.hashmap;

import java.util.LinkedHashMap;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/24 17:32
 * @Description
 */
public class linkedHashMap {
    public static void main(String[] args) {

        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put(1,1);
        map.put(2,1);
        map.put(3,1);

        map.remove(2);
    }
}
