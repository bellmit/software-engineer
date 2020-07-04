package com.runtime.algorithm.structure.tree.avltree;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/3 17:32
 * @Description 二叉平衡树
 */
public class AVLTree {
    public static void main(String[] args) {
        AVLTreeManager avlTreeManager = new AVLTreeManager();
        //int[] arr = {4,3,6,5,7,8};
        int[] arr = { 10, 12, 8, 9, 7, 6 };
        //创建一个 AVLTree对象
        //添加结点
        for (int i = 0; i < arr.length; i++) {
            avlTreeManager.add(new Node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTreeManager.infixOrder();


        System.out.println(avlTreeManager.getRoot().height());
        System.out.println(avlTreeManager.getRoot().leftHeight());
        System.out.println(avlTreeManager.getRoot().rightHeight());


    }
}

