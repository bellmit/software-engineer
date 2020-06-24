package com.runtime.algorithm.structure.linear.linkedlist.management;

import com.runtime.algorithm.structure.linear.linkedlist.bean.HeroNode;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/23 10:33
 * @Description - 新增(尾部追加/规则插入) | 查询 | 修改 | 删除
 */
public class SingleLinkedListT1 {
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode heroNode = new HeroNode(0, "", "");

    //返回头结点
    public HeroNode getHead() {
        return this.heroNode;
    }

    // TODO 1 : 新增 1. 添加节点到单向链表  1. 找到当前链表的最后节点  2. 将最后这个节点的next 指向 新的节点
    public void add(HeroNode heroNode) {

        //因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode tempNode = this.heroNode;
        //遍历链表,找到最后
        while (true) {
            //找到链表的最后
            if (tempNode.next == null) {
                break;
            }
            tempNode = tempNode.next;
        }

        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next 指向 新的节点
        tempNode.next = heroNode;

    }


    // 2. 根据排名插入到指定位置 (如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {

        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置 ||| 因为单链表，因为我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = this.heroNode;
        boolean flag = false; //flag 标志添加的标号是否存在,默认为false
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; //todo 后移，遍历当前链表
        }
        // 判断 flag 的值
        if (flag) {
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            //插入到链表中, temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // TODO 2 : 查询 显示链表[遍历]
    public void list() {
        if (this.heroNode.next == null) {
            System.err.println("链表为空");
            return;
        }

        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = this.heroNode.next;
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


    // TODO 3 : 删除
    public void delete(int index) {
        HeroNode temp = this.heroNode;
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                break;
            } // 链表最后
            if (temp.next.no == index) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.err.println("Id 不存在");
        }

    }

    // TODO 4 : 修改
    public void update(HeroNode heroNode) {
        HeroNode temp = this.heroNode;
        boolean flag = false;

        if (temp.next == null){
            System.err.println("链表为空");return;
        }

        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){temp.next.name = heroNode.name; temp.next.nickname = heroNode.nickname;}
        else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n",heroNode.no);
            System.err.println("没有");
        }

    }




}