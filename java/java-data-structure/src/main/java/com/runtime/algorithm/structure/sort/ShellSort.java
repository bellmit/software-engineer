package com.runtime.algorithm.structure.sort;

import java.util.Arrays;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/11 17:23
 * @Description 希尔排序 交换  移动
 */
public class ShellSort {
    public static void shellSortYd(int arr[]) {
        int temp = 0;
        for (int log = arr.length / 2; log > 0; log /= 2) {
            //System.out.println(log);
            for (int i = log; i < arr.length; i++) {
                int j = i;
                temp = arr[j];

                if (arr[j] < arr[j - log]) {
                    while (j - log >= 0 && temp < arr[j - log]) {
                        // System.out.println(arr[j] + " 00000 " + arr[j - log]);
                        arr[j] = arr[j - log];
                        j -= log;
                    }
                    arr[j] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void shellSort(int[] arr) {

        int temp = 0;
        for (int log = arr.length / 2; log >= 0; log /= 2) {
            for (int i = log; i < arr.length; i++) {
                for (int j = i - log; j >= 0; j -= log) {
                    if (arr[j] > arr[j + log]) {
                        temp = arr[j];
                        arr[j + log] = arr[j];
                        arr[j] = temp;
                    }
                }

            }
        }
    }
}
