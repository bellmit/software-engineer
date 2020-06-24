package com.runtime.algorithm.structure.linear.linkedlist.management;

import com.runtime.algorithm.structure.linear.linkedlist.bean.Boy;
import sun.awt.windows.ThemeReader;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/23 17:22
 * @Description 创建一个环形的单向链表
 */
public class CircleSingleLinkedList {
    //创建一个first 节点,
    static private Boy first = null;

    // todo 构建环形链表
    public static void addBoy(int nums) {
        if (nums < 1) {
            System.err.println("Nums 的值不正确");
            return;
        }

        Boy curBoy = null; //辅助指针,帮助构建环形链表

        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            //根据编号 创建小孩节点
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // todo 遍历 环形链表
    public static void showBoy() {
        if (first == null) {
            System.err.println("First 为 Null");
            return;
        }

        Boy curBoy = first;
        while (true) {
            System.out.printf("编号为 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    // todo 小孩出圈问题

    /**
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 对数据做校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.err.printf("参数输入错误%d", first.getNo());
        }

        //创建要给辅助指针,帮助小孩出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        //小孩报数前,先让first 和 helper 移动 k -1 次
        for (int i = 1; i < startNo ; i++) {
            //helper = first;
            first = first.getNext();
            helper = helper.getNext();
        }

        //当小孩报数时，让first 和 helper 指针同时 的移动  m  - 1 次, 然后出圈
        while (true) {
            if (helper == first) {
                break;
            } //圈中只有一个小孩
            // 让first 和 helper 指针同时 移动 countNum -1 位
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());

    }

    public void countBoyd(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        // 创建要给辅助指针,帮助完成小孩出圈
        Boy helper = first;
        // 需求创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) { // 说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让 first 和  helper 移动 k - 1次
        for(int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first 和 helper 指针同时 的移动  m  - 1 次, 然后出圈
        //这里是一个循环操作，知道圈中只有一个节点
        while(true) {
            if(helper == first) { //说明圈中只有一个节点
                break;
            }
            //让 first 和 helper 指针同时 的移动 countNum - 1
            for(int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first); //

        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());

    }
}

