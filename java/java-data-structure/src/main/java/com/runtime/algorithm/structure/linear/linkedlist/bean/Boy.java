package com.runtime.algorithm.structure.linear.linkedlist.bean;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/23 17:20
 * @Description
 */
public class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
