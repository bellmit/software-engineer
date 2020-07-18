package com.runtime.algorithm.structure.sort;

import java.util.HashSet;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/11 11:02
 * @Description 归并排序 [线性对数阶]
 */
public class MergeSort {

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        //分解 左递归 右递归 合并方法
        if (left < right) {
            int mid = (left + right) / 2;

            //左递归
            mergeSort(arr, left, mid, temp);
            //右递归
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);

        }

    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {


        int i = left;
        int j = mid + 1;
        int t = 0;
        // 按照左右两边有序的方式 填充进 temp
        while (i <= mid && j <= right) {

//            if (arr[i] < arr[j]) {
//                temp[t] = arr[i];
//                t += 1;
//                i += 1;
//            } else {
//                temp[t] = arr[j];
//                j += 1;
//                t += 1;
//            }

            temp[t++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        // 把剩余的数据拷贝进 temp
        while (i <= mid) {
//            temp[t] = arr[i];
//            t += 1;
//            i += 1;
            temp[t++] = arr[i++];
        }
        while (j <= right) {
//            temp[t] = arr[j];
//            t += 1;
//            j += 1;
            temp[t++] = arr[j++];
        }
        // 将temp 数据 合并到 原数组中
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft += 1;
            t += 1;
        }

    }


}
