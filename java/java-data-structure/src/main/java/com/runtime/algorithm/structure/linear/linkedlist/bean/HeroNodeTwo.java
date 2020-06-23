package com.runtime.algorithm.structure.linear.linkedlist.bean;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/23 15:17
 * @Description
 */
public class HeroNodeTwo {
    public int no;
    public String name;
    public String nickname;
    public HeroNodeTwo next; //指向下一个节点
    public HeroNodeTwo pre;

    //构造器
    public HeroNodeTwo(int no, String name, String nickname) {
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