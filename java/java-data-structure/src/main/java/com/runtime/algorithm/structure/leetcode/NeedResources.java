package com.runtime.algorithm.structure.leetcode;


import com.runtime.algorithm.structure.leetcode.zero_hundred.ListNode;
import com.runtime.algorithm.structure.leetcode.zero_hundred.OneTwoTwenty;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/2 9:02
 * @Description leetCode 所需要的 资源
 */
public class NeedResources {

    //todo LeetCode 第二题
    public static ListNode ot1(OneTwoTwenty twenty) {
        ListNode listNode1_1 = new ListNode(2);
        ListNode listNode1_2 = new ListNode(4);
        ListNode listNode1_3 = new ListNode(3);

        listNode1_1.next = listNode1_2;
        listNode1_2.next = listNode1_3;

        ListNode listNode2_1 = new ListNode(5);
        ListNode listNode2_2 = new ListNode(6);
        ListNode listNode2_3 = new ListNode(4);

        listNode2_1.next = listNode2_2;
        listNode2_2.next = listNode2_3;

        return twenty.addTwoNumbers(listNode1_1, listNode2_1);
    }
}
