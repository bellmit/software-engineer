package com.runtime.java.jvm;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/3 9:51
 * @Description
 */
public class JMM {
    public static void main(String[] args) {


      /*  ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            int index = i;
            new Thread(
                    () -> {
                        list.add(index);
                    }, "??"
            ).start();
        }

        for (int i = 0; i < 15; i++) {
            int index = i;
            new Thread(
                    () -> {
                        list.remove(index);
                    }, "??"
            ).start();
        }*/

/*        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(1, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }*/

        Map<Object, Object> map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            int indexTmep = i;
            new Thread(
                    () -> {
                        map.put(indexTmep, UUID.randomUUID());
                        System.out.println(map.get(indexTmep));
                    }, "??"
            ).start();
        }


    }
}
