package com.runtime.algorithm.structure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/1 13:50
 * @Description 赫夫曼树 构建出最优二叉树 并通过前序遍历输出
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};

        preOrder(createHuffmanTree(arr));
    }

    //创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<Node>();
        for (int every : arr) {
            nodes.add(new Node(every));
        }

        /**
         * todo 构建赫夫曼的方法
         * 1. 先排序
         * 2. 取出队列前两个权值
         * 3. 将权值相加 作为新二叉树的根节点 并构建一颗新的二叉树
         * 4. 根节点添加进 队列中 并从队列中移除之前的两个计算过的节点 [固定索引位置 0/1]
         * 5. 继续上述操作 直到队列中只有一个值
         */
        //* 5. 继续上述操作 直到队列中只有一个值
        while (nodes.size() > 1) {
            //* 1. 先排序
            nodes.sort(Node::compareTo);
            //* 2. 取出队列前两个权值
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //* 3. 将权值相加 作为新二叉树的根节点 并构建一颗新的二叉树
            Node newNode = new Node(leftNode.value + rightNode.value);
            newNode.left = leftNode;
            newNode.right = rightNode;
            //* 4. 根节点添加进 队列中 并从队列中移除之前的两个计算过的节点 [固定索引位置 0/1]
            nodes.add(newNode);
            nodes.remove(leftNode);
            nodes.remove(rightNode);
        }

        // 返回哈夫曼树的root结点
        return nodes.get(0);
    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            throw new RuntimeException("队列为空 不能遍历");
        }
    }

}


//创建节点类
class Node implements Comparable<Node> {
    int value;
    Node left;
    Node right;


    //编写一个前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    // 升序排列
    // 如果 降序 可以  -(this.value - o.value)
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}