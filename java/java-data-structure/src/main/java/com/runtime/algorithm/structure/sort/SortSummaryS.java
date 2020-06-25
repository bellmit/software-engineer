package com.runtime.algorithm.structure.sort;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/25 17:58
 * @Description
 */
public class SortSummaryS {

    // todo 冒泡排序 时间复杂度 为 O(n2)
    //todo 相邻两个数字 做大小运算 并左右替换
    public static void bubbleSort(int[] arr) {

        int temp = 0;
        boolean flag = false; //标识变量，表示是否进行过交换

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    //todo 时间复杂度 为 O(n2)
    //todo 思想: 找出数组中最小值 然后将该值 与数组左侧值交换 ++1 索引后 继续重复找寻
    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];

            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

        }

        System.out.println(Arrays.toString(arr));


    }

    public static void main(String[] args) {

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("开始时间: -> " + format.format(System.currentTimeMillis()));
        //bubbleSort(arr); //冒泡排序
        //selectSort(arr); //选择排序
        System.out.printf("结束时间: -> " + format.format(System.currentTimeMillis()));

    }


    /**
     *  for (int i = 0; i < arr.length - 1; i++) {
     *             int minIndex = i;
     *             int min = arr[i];
     *             for (int j = i + 1; j < arr.length; j++) {
     *                 if (min > arr[j]) { //说明假定的最小值，并不是最小
     *                     min = arr[j]; //重置min
     *                     minIndex = j; //重置minIndex
     *                 }
     *             }
     *
     *             if (minIndex != 0) {
     *                 arr[minIndex] = arr[i];
     *                 arr[i] = min;
     *             }
     *         }
     */

}


