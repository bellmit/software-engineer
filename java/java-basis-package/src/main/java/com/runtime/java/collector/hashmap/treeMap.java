package com.runtime.java.collections.hashmap;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/25 13:25
 * @Description
 */
public class treeMap {
    public static void main(String[] args) {
        TreeMap<Object, Object> map = new TreeMap<>();
        map.put(50, 1);
        map.put(45, 1);
        map.put(47, 1);

        if (args == null || args.length==0)

        //map.put(55, 1);

        //map.put();
        //map.get();
        //map.remove(47);
        for (Iterator<Map.Entry<Object, Object>> it = map.entrySet().iterator(); it.hasNext(); ) {
            System.out.println(it.next().getKey());
        }

            
        }
}
