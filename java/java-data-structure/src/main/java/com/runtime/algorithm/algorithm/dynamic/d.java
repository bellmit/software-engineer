package com.runtime.algorithm.algorithm.dynamic;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/13 10:18
 * @Description
 */
public class d {
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();

        map.put(1, 1);
        map.put(12, 1);

        System.out.println(6/0.75);


//        for (Map.Entry<Object, Object> objectObjectEntry : map.entrySet()) {
//            System.out.println(objectObjectEntry);
//        }

        ConcurrentHashMap<Object, Object> map1 = new ConcurrentHashMap<>();


        map.forEach((value1,value2) -> System.out.println(value1 + "="+value2));


//        for (Iterator<Map.Entry<Object, Object>> it = map.entrySet().iterator(); it.hasNext(); ) {
//            System.out.println(it.next());
//        }
    }
}
