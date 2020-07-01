package com.runtime.algorithm.structure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import static com.runtime.algorithm.structure.sort.HeapSort.HeapSortMethod;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/30 19:53
 * @Description 所有排序都在这个类中进行调用
 */
public class AllSortTestMain {
    public static void main(String[] args) {


        int[] arr = new int[8888888];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("开始时间: -> " + format.format(System.currentTimeMillis()));

        //HeapSortMethod(arr); // 堆排序


        //***** 小数据级
        int arr2[] = {1, 6, 8, 5, 9, 10, 20};
        System.out.println(Arrays.toString(arr2));
        System.out.println();

        HeapSortMethod(arr2);  //堆排序


        System.out.printf("结束时间: -> " + format.format(System.currentTimeMillis()));

    }
}
