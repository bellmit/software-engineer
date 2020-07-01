package com.runtime.algorithm.structure.leetcode.day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/1 8:30
 * @Description LeetCode 1 - 20
 */
public class OneTwoTwenty {

    //
    // 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
    //
    //
    //
    // 示例:
    //
    // 给定 nums = [2, 7, 11, 15], target = 9
    //
    //因为 nums[0] + nums[1] = 2 + 7 = 9
    //所以返回 [0, 1]
    //
    // Related Topics 数组 哈希表

    // todo 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标
    public int[] twoSum(int[] nums, int target) {

        int[] indexArr = new int[2];
        //todo method 1 | 将缺值存入到HashMap  后序调用 containsKey 方法取出
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                indexArr[0] = i;
                indexArr[1] = hashMap.get(nums[i]);

                return indexArr;
            }
            hashMap.put(target - nums[i], i);
        }


        //todo method 2  时间复杂度 | (n^n)
        /*for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] + nums[j] == target) {
                    indexArr[1] = i;
                    indexArr[0] = j;
                    return indexArr;
                }
            }
        }*/

        //todo method 3
        /*Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                indexArr[0] = i;
                indexArr[1] = map.get(complement);
                return indexArr;
            }
        }*/

        return indexArr;
    }
}
