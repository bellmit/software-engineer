package com.runtime.algorithm.structure.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/11 14:59
 * @Description 快速排序
 */
public class QuickSort {
    public static void quickSort(int arr[], int left, int right) {
        //todo 定义两个值 分别为 左下标 与 右下标
        int le = left;
        int ri = right;
        int temp = 0;
        //System.out.println(Arrays.toString(arr));
        //todo 定义中间值
        int pivot = arr[(left + right) / 2];
        //System.out.println((left + right) / 2);

        while (le < ri) {
            // 左边值 大于 pivot 则停止
            while (arr[le] < pivot) {
                le += 1;
            }
            // 右边值 小于 pivot 则停止
            while (arr[ri] > pivot) {
                ri -= 1;
            }
            // 左边 与 右边 排列完毕 (小于) | (大于)
            if (le >= ri) {
                break;
            }
            // 交换位置
            temp = arr[ri];
            arr[ri] = arr[le];
            arr[le] = temp;

            // if  arr[le] == pivot 相等 ri--
            if (arr[le] == pivot) {
                ri -= 1;
            }
            // if  arr[ri] == pivot 相等 le++
            if (arr[ri] == pivot) {
                le += 1;
            }
            System.out.println("ri " + ri);

        }
        // 如果 le == ri 则需要 (le ++) (ri --)
        if (le == ri) {
            le += 1;
            ri -= 1;
        }

        //开始递归
        if (left < ri) {
            quickSort(arr, left, ri); //从 头 到 ri次
        }
        if (right > le) {
            quickSort(arr, le, right); //从le 到 末尾
        }
    }
}
