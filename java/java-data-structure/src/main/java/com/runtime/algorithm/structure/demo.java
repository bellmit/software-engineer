package com.runtime.algorithm.structure;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/1 7:13
 * @Description
 */
public class demo {


    public int[] twoSum(int[] nums, int target) {


        int[] indexs = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] + nums[j] == target) {
                    indexs[0] = i;
                    indexs[1] = j;
                    return indexs;
                }
            }
        }
        return indexs;
    }

    public int[] twoSume(int[] nums, int target) {
        int[] indexs = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] + nums[j] == target) {
                    indexs[0] = i;
                    indexs[1] = j;
                    return indexs;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 4, 0, 1};
        System.out.println(Arrays.toString(new demo().twoSume(nums, 1)));
    }


}
