package com.runtime.algorithm.structure.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/4 9:08
 * @Description
 */
public class BinaryTree {

    //**************************************************
    // todo 94. 二叉树的中序遍历
    //**************************************************

    public List<Integer> inorderTraversal(TreeNodeInorderTraversal root) {

       /* if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> leftList = inorderTraversal(root.left);
        List<Integer> rightList = inorderTraversal(root.right);
        ArrayList<Integer> list = new ArrayList<>();


        list.addAll(leftList);
        list.add(root.val);
        list.addAll(rightList);

        return list;*/

        //*************************************************************************************
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNodeInorderTraversal> stack = new Stack<>();

        TreeNodeInorderTraversal cur = root;
        while (cur != null || !stack.isEmpty()) {
            //System.out.println(stack);
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }

    //**************************************************
    // todo 95. 不同的二叉搜索树
    //**************************************************

    public List<TreeNode> generateTrees(int n) {

        if (n <= 0) {
            return new ArrayList<>();
        }
        return generate_trees(1, n);
    }

    public LinkedList<TreeNode> generate_trees(int start, int end) {
        LinkedList<TreeNode> all_trees = new LinkedList<>();
        System.out.println("--- " + start + " ---- " + end);
        if (start > end) {
            all_trees.add(null);
            return all_trees;
        }

        // pick up a root
        for (int i = start; i <= end; i++) {
            // all possible left subtrees if i is choosen to be a root
            LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);

            // all possible right subtrees if i is choosen to be a root
            LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);

            // connect left and right trees to the root i
            for (TreeNode l : left_trees) {
                for (TreeNode r : right_trees) {
                    TreeNode current_tree = new TreeNode(i);
                    current_tree.left = l;
                    current_tree.right = r;
                    all_trees.add(current_tree);
                }
            }
        }
        return all_trees;
    }


}



