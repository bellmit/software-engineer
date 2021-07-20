package com.runtime.java.collector.list;

import java.util.*;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/22 14:40
 * @Description
 */
public class ArrayListInstance {

    static List<Object> list = new ArrayList<>();

/*    static {
        for (int i = 0; i < 1000000; i++) {

            list.add(i);
        }
    }*/

    public static void main(String[] args) throws InterruptedException {

        long l = System.currentTimeMillis();

      /*  for (Iterator<Object> it = list.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }*/


        //List<String> list1 = new Vector<>();
        //List<String> list1 = Collections.synchronizedList(new ArrayList<>());


   /*     List<String> list1 = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {

                list1.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list1);
            }, "i").start();
        }
*/


        HashMap<Object, Object> map = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            new Thread(
                    () -> {
                        map.put(random.nextInt(666), random.nextInt(888));
                        System.out.println(map);
                    }, Thread.currentThread().getName()
            ).start();
            new Thread(

                    () -> {
                       // map.put(random.nextInt(666), random.nextInt(888));
                        System.out.println(map);
                    }, "??"
            ).start();
        }


        System.out.println(System.currentTimeMillis() - l);




/*
        ListIterator<Object> iterator = list.listIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/


    }

}
