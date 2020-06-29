package com.runtime.algorithm.structure.hashTable;

import com.sun.media.jfxmediaimpl.HostUtils;

import java.util.Scanner;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/29 16:32
 * @Description
 */
public class HashTable {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("delete: 删除雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "delete":
                    System.out.println("请输入要删除的雇员Id");
                    id = scanner.nextInt();
                    hashTab.deleteEmp(id);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.uidGetEmp(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }


    }
}

//创建HashTab 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size; //表示有多少条链表
    //构造器

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();

        }
    }

    //添加雇员
    public void add(Emp emp) {
        this.empLinkedListArray[hashFun(emp.id)].add(emp);

    }

    //遍历所有的链表,遍历hashtab
    public void list() {
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i].list(i);

        }
    }

    //根据输入的id,查找雇员
    public void uidGetEmp(int id) {
        if (empLinkedListArray[hashFun(id)].findEmpById(id) == null) {
            System.err.println("没找到");
        } else {
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (hashFun(id) + 1), id);
        }
    }

    //根据Id 删除雇员
    public void deleteEmp(int id) {
        empLinkedListArray[hashFun(id)].deleteEmp(id);
    }

    public int hashFun(int id) {
        return id % this.size;
    }
}


//雇员类
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList ,表示链表
class EmpLinkedList {
    //头指针，执行第一个Emp,因此我们这个链表的head 是直接指向第一个Emp
    private Emp head; //默认null

    //添加雇员到链表
    //说明
    //1. 假定，当添加雇员时，id 是自增长，即id的分配总是从小到大
    //   因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {//说明到链表最后
                break;
            }
            curEmp = curEmp.next; //后移
        }
        //退出时直接将emp 加入链表
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) { //说明链表为空
            System.out.println("第 " + (no + 1) + " 链表为空");
            return;
        }
        System.out.print("第 " + (no + 1) + " 链表的信息为");
        Emp curEmp = head; //辅助指针
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {//说明curEmp已经是最后结点
                break;
            }
            curEmp = curEmp.next; //后移，遍历
        }
        System.out.println();
    }

    //根据id查找雇员
    //如果查找到，就返回Emp, 如果没有找到，就返回null
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//找到
                break;//这时curEmp就指向要查找的雇员
            }
            //退出
            if (curEmp.next == null) {//说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;//以后
        }

        return curEmp;
    }

    //删除雇员
    public void deleteEmp(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }

        Emp curEmp = head;
        while (true) {
            if (curEmp.next.id == id) {
                curEmp = curEmp.next.next;
                break;
            }
            curEmp = curEmp.next;
        }

        this.head = curEmp;

    }

}