package com.runtime.algorithm.structure.linear.linkedlist;

import com.runtime.algorithm.structure.linear.linkedlist.bean.HeroNode;
import com.runtime.algorithm.structure.linear.linkedlist.management.SingleLinkedListT1;


/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/22 18:38
 * @Description 链表
 */
public class SingleLinkedListClientT1 {
    public static void main(String[] args) {


        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedListT1 singleLinkedList = new SingleLinkedListT1();

        //加入
        singleLinkedList.addByOrder(hero4);
        //  singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        //删除
        singleLinkedList.delete(40);
        //singleLinkedList.list();

        //修改
        singleLinkedList.update(new HeroNode(22,"小刘","年兽"));

        //查询
        singleLinkedList.list();


    }
}
