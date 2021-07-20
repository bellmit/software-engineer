package com.runtime.algorithm.structure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Queue;

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
        System.out.println(2 / 2);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("开始时间: -> " + format.format(System.currentTimeMillis()));

        //HeapSortMethod(arr); // 堆排序  3s
        //InsertSort.insertSort(arr); //选择排序 6 minute 没跑完
        //RadixSort.radixSort(arr); //基数排序/桶排序 1s
        //MergeSort.mergeSort(arr, 0, arr.length-1, new int[arr.length ]); //归并排序 2s
        //BubbleSort.bubbleSort(arr); //冒泡
        //QuickSort.quickSort(arr,0,arr.length-1); //快排 2s


        //***** 小数据级
        //int arr2[] = {1, 6, 8, 5, 9, 10, 20};
        int arr2[] = {3, 10, 30, 7, 9, 2, 100, 0};
        System.out.println(Arrays.toString(arr2));
        System.out.println();

        //HeapSortMethod(arr);  //堆排序
        //InsertSort.insertSort(arr2); //插入排序
        //RadixSort.radixSort(arr2); //基数排序/桶排序
        //MergeSort.mergeSort(arr2, 0, arr2.length-1, new int[arr.length ]); //归并排序
        BubbleSort.bubbleSort(arr2); //冒泡排序
        //QuickSort.quickSort(arr2, 0, arr2.length - 1); 快速排序
        //ShellSort.shellSortYd(arr2); //希尔排序 移动法
        //ShellSort.shellSort(arr2); //希尔排序 交换法

        System.out.println(Arrays.toString(arr2));
        System.out.printf("结束时间: -> " + format.format(System.currentTimeMillis()));

    }
}
