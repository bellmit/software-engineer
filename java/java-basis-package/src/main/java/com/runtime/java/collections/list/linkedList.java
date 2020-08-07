package com.runtime.java.collections.list;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/23 19:52
 * @Description
 */
public class linkedList {
    static long millis = System.currentTimeMillis();


    static LinkedList<Integer> list = new LinkedList<>();

    static {

        for (int i = 0; i < 8; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {


        //LinkedList<Object> list1 = new LinkedList<>();
        //list.addAll(list);
        //list.remove(1);

        for (Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }

        System.out.println();
        System.out.println(System.currentTimeMillis() - millis);

    }
}
//china's gdp growth quarterly