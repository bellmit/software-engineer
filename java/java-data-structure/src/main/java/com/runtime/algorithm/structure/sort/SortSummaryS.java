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


    //todo 时间复杂度 O(n 1.3)
    //todo 思想 : 按下标增量分组,组内使用插入排序,直到增量减至1
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


    //todo 时间复杂度 O(n 1.3)
    //todo
    public static void shellSortYd(int[] arr) {
        int temp = 0;
        for (int log = arr.length / 2; log > 0; log /= 2) { //5 2 1

            for (int i = log; i < arr.length; i++) { //5 6 7 8 9
                int j = i; // 5 6 7 8 9
                temp = arr[j]; // arr[5]

                if (arr[j] < arr[j - log]) { // arr[5] < arr[0] arr[6] < arr[1] arr[7] < arr[2]
                    while (j - log >= 0 && temp < arr[j - log]) { // 3 2 1 arr[3] arr[2] arr[1]
                        arr[j] = arr[j - log];
                        j -= log;
                    }
                    arr[j] = temp;
                }
            }

            // System.out.println(Arrays.toString(arr));
        }


    }


    //todo 时间复杂度 O(n log2 n)
    //todo 思想 : 冲数组中定义出一个中间值 判断左右值 并替换位置 (递归方法 直到结束)
    public static void quickSort(int arr[], int left, int right) {
        //todo 定义两个值 分别为 左下标 与 右下标
        int le = left;
        int ri = right;
        int temp = 0;
        //todo 定义中间值
        int pivot = arr[(left + right) / 2];

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

    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);

            merge(arr, left, mid, right, temp);
        }

    }

    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        //先把左右两边(有序)的数据按照规则填充到temp数组
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //把剩余的数据的一边的数据依次全部填充到temp中
        while (i <= mid) {
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //将temp数组的元素拷贝到arr
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }


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
        //shellSort(arr); //希尔排序交换法
        //shellSortYd(arr); //希尔排序移动法
        //quickSort(arr, 0, arr.length - 1); // 快速排序


        int[] arr1 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0, 2};
        //System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr1));
        System.out.println();


        int[] temp = new int[arr1.length];
        mergeSort(arr1, 0, arr1.length - 1, temp);
        System.out.println(Arrays.toString(arr1));
        //quickSort(arr, 0, arr1.length - 1);

        System.out.printf("结束时间: -> " + format.format(System.currentTimeMillis()));

    }


}


