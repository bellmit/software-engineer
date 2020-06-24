package com.runtime.algorithm.structure.linear.linkedlist;

import com.runtime.algorithm.structure.linear.linkedlist.management.CircleSingleLinkedList;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/23 17:19
 * @Description 约瑟夫问题 环形链表
 */
public class Joseph {
    public static void main(String[] args) {
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        // 构建 环形 链表
        linkedList.addBoy(125);

        // 遍历输出啊 链表
        //linkedList.showBoy();


        // 小孩儿 出圈问题
        linkedList.countBoy(10,20,125);
        //linkedList.countBoyd(1,2,5);

    }

}




