package com.runtime.algorithm.structure.leetcode.day;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/30 6:38
 * @Description
 */
public class Three_398_Solution {

    public static void main(String[] args) {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        Three_398_Solution solution = new Three_398_Solution(arr);
        System.out.println(solution.pick(3));

    }

    static private int[] nums;
    static private int point;

    public Three_398_Solution(int[] nums) {
        this.nums = nums;
    }

    /*public int pick(int target) {

        for (; ; ) {
            point++;
            if (target == this.nums[point % nums.length - 1]) {
                return point % nums.length - 1;
            }
            if (point >nums.length-1){
                point = 0;
            }
        }

    }*/

    public int pick(int target) {
        Random random = new Random();
        int n = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                n++;
                if (random.nextInt() % n == 0) {
                    index = i;
                }
            }
        }
        return index;
    }

}
