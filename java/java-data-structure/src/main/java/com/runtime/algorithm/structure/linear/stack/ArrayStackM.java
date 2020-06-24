package com.runtime.algorithm.structure.linear.stack;

import java.util.Scanner;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/24 10:12
 * @Description 用 Array实现 stack 操作
 */
public class ArrayStackM {
    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~~");
    }


}

//定义一个 ArrayStack 表示栈
class ArrayStack {

    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈 - push
    public void push(int value) {
        //先判断 栈是否满
        if (isFull()) {
            System.err.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈 - pop
    public int pop() {
        //先判断 是否为空
        if (isEmpty()) {
            System.err.println("栈空");
            throw new RuntimeException("栈空 没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 遍历 栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        for (int i = top; i > 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}