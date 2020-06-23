package com.runtime.algorithm.structure.InterviewQuestions.linkedList;

import com.runtime.algorithm.structure.linear.linkedlist.bean.HeroNode;
import com.runtime.algorithm.structure.linear.linkedlist.management.SingleLinkedListT1;

import java.util.Stack;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/23 12:23
 * @Description 求单链表中有效节点的个数
 */
public class QuestionOne {

    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(11, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(12, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(13, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(14, "林冲", "豹子头");


        //创建要给链表
        SingleLinkedListT1 singleLinkedList = new SingleLinkedListT1();

        //加入
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);


        //System.out.println(getLength(singleLinkedList.getHead()));

        //System.out.println(findLastIndexNode(singleLinkedList.getHead(), 1).toString());

        //reversePrint(singleLinkedList.getHead());


        connectTwoHeroNode(singleLinkedList.getHead(), returnHeroNode().getHead());
        //mergeLinkList(singleLinkedList.getHead(), returnHeroNode().getHead());

    }

    /**
     * @param head
     * @return 返回有限个数
     * @Description todo 求单链表中有效节点的个数
     */
    public static int getLength(HeroNode head) {

        if (head.next == null) {
            return 0;
        } //空链表

        //定义一个辅助的变量, 这里我们没有统计头节点
        int length = 0;
        while (head.next != null) {
            ++length;
            head = head.next; //遍历
        }

        return length;
    }


    /**
     * @param head
     * @param index 倒数第 n 个
     * @return 返回 倒数第 n 个 节点
     * @Description todo 查找单链表中的倒数第k个节点
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {

        if (head.next == null) {
            System.err.println("空列表");
            return null;
        }
        int size = getLengths(head);

        if (index < 0 || index > size) {
            System.err.println("传入Index 不正确");
            return null;
        }

        //定义给辅助变量， for 循环定位到倒数的index
        HeroNode cur = head.next; //3 // 3 - 1 = 2
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;

    }


    /**
     * @param head
     * @Description todo 单链表的反转
     */
    public static void reversetList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            System.err.println("链表为空");
            return;
        }

        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        //指向 下一个节点
        HeroNode next = null;
        // 定义新的头部
        HeroNode node = new HeroNode(1, "", "");

        while (cur != null) {
            next = cur.next;//暂存下一个节点
            cur.next = node.next;
            node.next = cur;
            cur = next;
        }
        // 将 头部在接回去
        head.next = node.next;
        list(head);
    }

    /**
     * @param head
     * @Description todo: 从尾到头打印单链表  可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
     */
    public static void reversePrint(HeroNode head) {

        if (head.next == null) {
            return; //空链表,不能打印
        }
        // 创建一个栈.将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;

        //将链表的所有节点压进栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; // cur 后移 顺序压入
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }


    }


    //合并两个有序的单链表，合并之后的链表依然有序
    public static HeroNode connectTwoHeroNode(HeroNode head1, HeroNode head2) {

        if (head1 == null || head2 == null) {
            // 如果两个链表都为空
            return null;
        }

        if (head1 == null){return head2;}
        if (head2 == null){return head1;}

        HeroNode head; // 新链表的头结点
        HeroNode current; // current结点指向新链表
        // 一开始，我们让current结点指向head1和head2中较小的数据，得到head结点

        if (head1.no < head2.no) {
            head = head1;
            current = head1;
            head1 = head1.next;
        } else {
            head = head2;
            current = head2;
            head2 = head2.next;
        }
        while (head1 != null && head2 != null) {
            if (head1.no < head2.no) {
                current.next = head1;
                // 新链表中，current指针的下一个结点对应较小的那个数据
                current = current.next; // current指针下移
                head1 = head1.next;
            } else {
                current.next = head2;
                current = current.next;
                head2 = head2.next;
            }
        }
        // 合并剩余的元素
        if (head1 != null) {
            // 说明链表2遍历完了，是空的
            current.next = head1;
        }
        if (head2 != null) {
            // 说明链表1遍历完了，是空的
            current.next = head2;
        }

        list(head);

        return head;
    }

    // 获取长度
    public static int getLengths(HeroNode heroNode) {
        // todo 1: 求出 链表中所有 长度
        int count = 0;
        while (heroNode.next != null) {
            ++count;
            heroNode = heroNode.next;
        }
        return count;
    }

    // 遍历
    public static void list(HeroNode heroNode) {
        if (heroNode.next == null) {
            System.err.println("链表为空");
            return;
        }

        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = heroNode.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            // todo : 链表后移
            temp = temp.next;
        }
    }

    //新增
    public static SingleLinkedListT1 returnHeroNode() {

        HeroNode hero11 = new HeroNode(111, "宋江", "及时雨");
        HeroNode hero22 = new HeroNode(122, "卢俊义", "玉麒麟");
        HeroNode hero33 = new HeroNode(133, "吴用", "智多星");
        HeroNode hero44 = new HeroNode(144, "林冲", "豹子头");

        SingleLinkedListT1 singleLinkedListTwo = new SingleLinkedListT1();

        singleLinkedListTwo.addByOrder(hero11);
        singleLinkedListTwo.addByOrder(hero22);
        singleLinkedListTwo.addByOrder(hero33);
        singleLinkedListTwo.addByOrder(hero44);
        return singleLinkedListTwo;
    }


    public static HeroNode mergeLinkList(HeroNode head1, HeroNode head2) {
        if (head1 == null || head2 == null) {
            // 如果两个链表都为空
            return null;
        }

        HeroNode head; // 新链表的头结点
        HeroNode current; // current结点指向新链表
        // 一开始，我们让current结点指向head1和head2中较小的数据，得到head结点
        if (head1.no < head2.no) {
            head = head1;
            current = head1;
            head1 = head1.next;
        } else {
            head = head2;
            current = head2;
            head2 = head2.next;
        }
        while (head1 != null && head2 != null) {
            if (head1.no < head2.no) {
                current.next = head1;
                // 新链表中，current指针的下一个结点对应较小的那个数据
                current = current.next; // current指针下移
                head1 = head1.next;
            } else {
                current.next = head2;
                current = current.next;
                head2 = head2.next;
            }
        }
        // 合并剩余的元素
        if (head1 != null) {
            // 说明链表2遍历完了，是空的
            current.next = head1;
        }
        if (head2 != null) {
            // 说明链表1遍历完了，是空的
            current.next = head2;
        }

        list(head);

        return head;
    }

}
