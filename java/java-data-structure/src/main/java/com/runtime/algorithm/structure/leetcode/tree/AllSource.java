package com.runtime.algorithm.structure.leetcode.tree;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/4 9:09
 * @Description
 */

//94 题用到
class TreeNodeInorderTraversal {
    int val;
    TreeNodeInorderTraversal left;
    TreeNodeInorderTraversal right;

    TreeNodeInorderTraversal(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNodeInorderTraversal{" +
                "val=" + val +
                '}';
    }

    public static TreeNodeInorderTraversal getTreeNodeInorderTraversal() {
        TreeNodeInorderTraversal traversal1 = new TreeNodeInorderTraversal(1);
        TreeNodeInorderTraversal traversal2 = new TreeNodeInorderTraversal(2);
        TreeNodeInorderTraversal traversal4 = new TreeNodeInorderTraversal(3);
        traversal1.right = traversal2;
        traversal2.left = traversal4;
        return traversal1;
    }
}

//95 题用到
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}
