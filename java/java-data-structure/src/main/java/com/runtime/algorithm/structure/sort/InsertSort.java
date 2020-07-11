package com.runtime.algorithm.structure.sort;

import java.util.Arrays;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/11 8:47
 * @Description 插入排序
 */
public class InsertSort {
    /**
     * 把 待排序的元素 看做一个有序跟无序队列
     * 从有序队列中取出1个元素 无需表中有n-1的元素
     * 从无需表中取出一个元素 跟 有序队列中的元素排序 插入到对应的位置
     */
    public static void insertSort(int[] arr) {
        int mid;
        int midIndex;


        for (int i = 1; i < arr.length - 1; i++) {
            mid = arr[i];
            midIndex = (i - 1);

            while (midIndex >= 0 && mid < arr[midIndex]) {
                arr[midIndex + 1] = arr[midIndex];
                midIndex--;
            }

            if (midIndex + 1 != i) {
                arr[midIndex + 1] = mid;
            }
        }
        //System.out.println(Arrays.toString(arr));
    }

}
