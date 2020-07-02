package com.runtime.algorithm.structure.leetcode.zero_hundred;


import java.util.HashMap;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/1 8:30
 * @Description LeetCode 1 - 20
 */
public class OneTwoTwenty {

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


    // todo 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null || carry != 0) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }


}
