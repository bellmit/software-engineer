package com.runtime.algorithm.structure.linear.linkedlist;

import com.runtime.algorithm.structure.linear.linkedlist.bean.HeroNode;
import com.runtime.algorithm.structure.linear.linkedlist.bean.HeroNodeTwo;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/23 15:17
 * @Description
 */
public class TwoLinkedList {
    static HeroNodeTwo TEMP = new HeroNodeTwo(0, "", "");

    public static void main(String[] args) {


        HeroNodeTwo hero1 = new HeroNodeTwo(10, "宋江", "及时雨");
        HeroNodeTwo hero2 = new HeroNodeTwo(12, "卢俊义", "玉麒麟");
        HeroNodeTwo hero3 = new HeroNodeTwo(14, "吴用", "智多星");
        HeroNodeTwo hero4 = new HeroNodeTwo(20, "林冲", "豹子头");


        // 添加
        add(hero1);
        add(hero2);
        add(hero3);
        add(hero4);

        // 查询
        //getQue();

        // 删除
        //del(14);

        // 修改
        // update(new HeroNodeTwo(14, "小宋", "123"));

        // 顺序插入
        addByOrder(new HeroNodeTwo(17, "ss", "11"));


    }


    //增加
    public static void add(HeroNodeTwo heroNodeTwo) {

        HeroNodeTwo temp = TEMP;

        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        // 当退出 while 循环时，temp就指向了链表的最后
        // 形成一个双向链表
        temp.next = heroNodeTwo;
        heroNodeTwo.pre = temp;
    }

    // 指定顺序插入
    public static void addByOrder(HeroNodeTwo heroNode) {
        if (TEMP.next == null) {
            return;
        }

        HeroNodeTwo temp = TEMP.next;

        boolean flag = false;
        while (true) {
            if (temp == null) {
                System.out.println("有了");
                break;
            }
            if (heroNode.no < temp.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.pre.next = heroNode;
            heroNode.next = temp;

        }

        getQue();
    }

    //删
    public static void del(int no) {

        if (TEMP.next == null) {
            return;
        }
        HeroNodeTwo temp = TEMP.next;
        boolean flag = false;

        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {

            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        }

        getQue();
    }

    //修改
    public static void update(HeroNodeTwo heroNode) {
        if (TEMP.next == null) {
            return;
        }
        boolean flag = false;
        HeroNodeTwo temp = TEMP.next;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }

        getQue();

    }

    //查询
    public static void getQue() {
        HeroNodeTwo temp = TwoLinkedList.TEMP.next;

        if (temp.next == null) {
            return;
        }
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}
