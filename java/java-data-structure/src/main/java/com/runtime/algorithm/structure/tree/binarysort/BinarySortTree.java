package com.runtime.algorithm.structure.tree.binarysort;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/2 18:01
 * @Description
 */
public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTreeSets treeSets = new BinarySortTreeSets();
        for (int i = 0; i < arr.length; i++) {
            treeSets.add(new Node(arr[i]));

        }
        treeSets.infixOrder();
    }
}


//创建二叉排序树
class BinarySortTreeSets {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //todo 添加操作
    public void add(Node node) {
        if (root != null) {
            root.add(node);
        } else {
            root = node;
        }
    }

    //todo 中序遍历
    public void infixOrder() {
        if (this.root == null) {
            System.err.println("二叉树为空");
            return;
        }
        root.infixOrder();
    }
}


// 创建节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加结点的方法
    //递归的形式添加结点，注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }

        //传入的节点的值 跟当前节点进行对比
        if (node.value < this.value) {
            if (this.left == null) {//如果当前结点左子结点为null 那么直接挂起
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else { //添加的结点的值大于 当前结点的值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //todo 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}