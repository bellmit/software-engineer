package com.runtime.algorithm.structure.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/4 9:08
 * @Description
 */
public class BinaryTree {
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

}



