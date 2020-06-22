package com.runtime.algorithm.structure.linear.linkedlist;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/22 18:38
 * @Description 链表
 */
public class SingleLinkedListT1 {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //加入
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);


        // 测试一下单链表的反转功能
        System.out.println("原来链表的情况~~");
        singleLinkedList.list();
    }
}

// 管理
class SingleLinkedList {
    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode heroNode = new HeroNode(0, "", "");

    //返回头结点
    public HeroNode getHead() {
        return this.heroNode;
    }

    //添加节点到单向链表  1. 找到当前链表的最后节点  2. 将最后这个节点的next 指向 新的节点
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


    //根据排名插入到指定位置 (如果有这个排名，则添加失败，并给出提示)
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
            System.out.println(heroNode.next);
            System.out.println(temp.next);
            temp.next = heroNode;
        }

        /* HeroNode temp = this.heroNode; // 初始节点
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else {
                if (temp.next.no == heroNode.no) {
                    flag = true;
                    break;
                }
            }
            temp = temp.next;
        }
        if (flag) {
            System.err.println("存在已知Uesr");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
*/

    }


    //显示链表[遍历]
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
}

//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}