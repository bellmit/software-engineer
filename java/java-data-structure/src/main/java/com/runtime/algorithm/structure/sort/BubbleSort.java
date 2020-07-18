package com.runtime.algorithm.structure.sort;

import java.util.Arrays;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/25 17:35
 * @Description 冒泡排序
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {

        //int[] arr = {1, 23, 2, 53, -1, 30};
        //int[] arr = {-1, 23, 234, 535, 1555};


        int temp = 0;
        boolean flag = false;
        //时间复杂度为 O(n2) 平方阶
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


}
