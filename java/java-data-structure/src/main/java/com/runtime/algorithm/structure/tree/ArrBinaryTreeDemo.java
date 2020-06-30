package com.runtime.algorithm.structure.tree;

import java.util.ArrayList;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/30 13:48
 * @Description 顺序二叉树 前/中/后 序遍历
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        new ArrBinaryTree(arr).preOrder();

    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }


    //todo 编写一个方法 完成顺序二叉树的前序遍历
    public void preOrder(int index) {

        if (arr == null || arr.length == 0) {
            System.err.println("数组为空");
        }

        //todo : 前序遍历 输出当前这个元素
        //System.out.println(arr[index]);

        //向左递归
        if (index * 2 + 1 < arr.length) {
            preOrder(index * 2 + 1);
        }

        //todo : 中序遍历 输出当前这个元素
        System.out.println(arr[index]);

        //向右递归
        if (index * 2 + 2 < arr.length) {
            preOrder(index * 2 + 2);
        }
        //todo : 后序遍历 输出当前这个元素
        //System.out.println(arr[index]);

    }

}