package com.runtime.algorithm.structure;

import java.util.Random;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/28 20:36
 * @Description 每天 随机 取一题来练习
 */
public class EveryDayTestThree {
    static String[] SORT = {"选择排序", "冒泡排序", "插入排序", "快速排序", "归并排序", "希尔排序", "基数排序"};
    static String[] QUERY = {"二分查找", ""};
    static String[] ALL = {"LinkedList", "Stack", "Array", ""};
    static Random RANDOM = null;

    static {
        RANDOM = new Random();
    }

    public static void main(String[] args) {
        //早上
        System.out.println(SORT[RANDOM.nextInt(SORT.length - 1)]);

        //中午
        System.out.println(QUERY[RANDOM.nextInt(QUERY.length - 1)]);

        //晚上
        System.out.println(ALL[RANDOM.nextInt(ALL.length - 1)]);


    }

}
