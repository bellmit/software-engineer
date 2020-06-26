package com.runtime.algorithm.structure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/25 17:58
 * @Description
 */
public class SortSummaryS {

    //todo 冒泡排序 时间复杂度 为 O(n2)
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


    //todo 时间复杂度 O(n2)
    //todo 思想 : 将一个数组 抽象成两个数组 取 index ++ 位置的值 与 左侧数组中所有值进行比较
    public static void insertSort(int[] arr) {

        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = (i - 1);

            while (insertIndex >= 0 && insertVal > arr[insertIndex]) { // insertIndex >= 0 && arr[1] < arr[0]
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
        //System.out.println(Arrays.toString(arr));
    }


    //todo
    //todo
    public static void shellSort(int[] arr) {
        //[8, 9, 1, 7, 2,  3, 5, 4, 6, 0]
        //[3, 5, 1, 6, 0,  8, 9, 4, 7, 2]
        //[0, 2, 1, 4, 3,  5, 7, 6, 9, 8]


        int temp = 0;

        for (int log = arr.length / 2; log > 0; log /= 2) {

            for (int i = log; i < arr.length; i++) {

                for (int j = i - log; j >= 0; j -= log) {

                    if (arr[j] < arr[j + log]) {

                        temp = arr[j + log];
                        arr[j + log] = arr[j];
                        arr[j] = temp;
                    }

                }
            }
        }

         System.out.println(Arrays.toString(arr));

    }


    public static void main(String[] args) {

        int[] arr = new int[88888];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("开始时间: -> " + format.format(System.currentTimeMillis()));
        //bubbleSort(arr); //冒泡排序
        //selectSort(arr); //选择排序
        //insertSort(arr); //插入排序
        int[] arr1 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr1);

        System.out.printf("结束时间: -> " + format.format(System.currentTimeMillis()));

    }


}


