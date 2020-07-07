package com.runtime.algorithm.algorithm.query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/7 20:02
 * @Description 二分查找算法  ||  二分查找的前提是 队列需要是有序的
 */
public class DichotomyQuery {

    public static void main(String[] args) {

        int[] arr = {1, 3, 4, 6, 7, 8, 2463};
        int arrl[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 20, 20};

        System.out.println(arrl.length);
        //System.out.println(non_RecursiveMethod(arr, 8));
        //System.out.println(recursiveMethod(arr, 222, 0, arr.length - 1));
        System.out.println(returnAllTargetIndex(arrl, 3, 0, arrl.length));
    }

    //todo 非递归方式
    public static int non_RecursiveMethod(int[] arr, int target) {
        /**
         * 1. 判断数组长度
         * 2. 定义两个指针 (left/right) 分别指向 数组的头节点与尾结点
         * 3. 定义一个中间索引 值为 left+right /2
         * 4. 将当前索引对应值 与 期望目标值做匹配 并根据条件 指针进行改变
         */

        // 判断数组长度 为空则没必要继续判断
        if (arr == null) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = right + left / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    //todo 递归方式
    public static int recursiveMethod(int[] arr, int target, int left, int right) {
        /**
         * 递归的终止条件 ? : 当传入的left > right 时 返回 -1
         * 定义中间索引 (传入的left + 传入的right) / 2
         * target 与 索引对应值做匹配 并根据条件 进行递归
         */

        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        if (target > arr[mid]) {
            return recursiveMethod(arr, target, mid + 1, right);
        } else if (target < arr[mid]) {
            return recursiveMethod(arr, target, left, mid - 1);
        } else {
            return mid;
        }
    }

    //todo 有多个相同的数值时，将所有的数值都查找到
    public static List<Integer> returnAllTargetIndex(int[] arr, int target, int left, int right) {

        /***
         * 根据数组时有序的特性  那么相邻值应该都为 n  则判断 n-1 && n+1 == n
         */
        if (left > right) {
            return new ArrayList<>();
        }


        int mid = (left + right) / 2;
        if (target > arr[mid]) {
            return returnAllTargetIndex(arr, target, mid + 1, right);
        } else if (target < arr[mid]) {
            return returnAllTargetIndex(arr, target, left, mid - 1);
        } else {
            ArrayList<Integer> list = new ArrayList<>();

            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != target) {
                    break;
                }
                list.add(temp);
                temp -= 1;
            }
            list.add(mid);  //

            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != target) {
                    break;
                }
                list.add(temp);
                temp += 1;
            }


            return list;
        }
    }
}

