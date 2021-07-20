package com.runtime.algorithm.Ms;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/3/9 8:34
 * @Description leetcode 给定一个整型数组（数组中的元素可重复），以及一个指定的值。打印出数组中两数之和为指定值的 所有整数对
 */
public class IntegerArrEqualsNum {

    public static void main(String[] args) {


        int[] arr = {1, 3, 3, 4, 0, 2, 2};
        int num = 5;
        // expectSum_bySort(arr, num);
        expectSum_bySet(arr, num);
        //        for (int i = 0, j = arr.length - 1; i < arr.length; i++, j--) {
//            if (arr[i] + arr[j] == num) {
//                System.out.println(arr[i] + "---" + arr[j]);
//            }
//        }


    }


    public static void expectSum_bySort(int[] arr, int expectSum) {

        if (arr == null || arr.length == 0) return;
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] > expectSum) {
                right--;
            } else if (arr[left] + arr[right] < expectSum) {
                left++;
            } else {
                System.out.println(arr[left] + " + " + arr[right] + " = " + expectSum);
                left++;
                right--;
            }
        }
    }

    public static void expectSum_bySet(int[] arr, int expectSum) {
        if (arr == null || arr.length == 0) return;
        HashSet<Integer> set = new HashSet<>(arr.length);

        int suplement;
        for (int i : arr) {
            suplement = expectSum - i;
            if (!set.contains(suplement)) {
                set.add(i);
            } else {
                System.out.println(i + " + " + suplement + " = " + expectSum);
            }
        }
    }


}
