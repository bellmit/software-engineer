package com.runtime.algorithm.structure.tree;

import com.runtime.algorithm.structure.linear.linkedlist.bean.HeroNodeTwo;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/30 7:23
 * @Description
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        HeroNode node6 = new HeroNode(6, "薛大虫");

        tree.setRoot(root);

        root.setLeft(node2);
        node2.setLeft(node3);
        node2.setRight(node4);

        root.setRight(node5);
        node5.setRight(node6);


//        HeroNode node = tree.preOrderSearch(1);
//        if (node != null) {
//            System.out.println(node);
//        } else {
//            System.out.println("没有找到");
//        }


//        //前序遍历
//        System.out.println("前序遍历"); //
//        tree.preOrder();

//        //中序遍历
//        System.out.println("中序遍历"); // 21534
//        tree.infixOrder();

        //后序遍历
        System.out.println("后序遍历"); // 25431
        tree.postOrder();
    }
}

//定义二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.proOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //todo 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            System.err.println("链表为空");
            return null;
        }
    }


    //todo 中序遍历
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            System.err.println("链表为空");
            return null;
        }
    }

    //todo 后序遍历
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            System.err.println("链表为空");
            return null;
        }
    }

}

//先创建 HoroNode 节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + "]";
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //编写前序遍历的方法
    public void proOrder() {
        System.out.println(this); // 先输出父节点
        if (this.left != null) {
            this.left.proOrder();
        }
        if (this.right != null) {
            this.right.proOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //todo 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        // 先判断当前节点是不是
        System.out.println(this);

        if (this.no == no) {
            return this;
        }
        HeroNode heroNode = null;

        // 如果当前节点的左节点 不为 null 则 向左递归查询
        if (this.left != null) {
            heroNode = this.left.preOrderSearch(no);
        }
        // 如果不为null 则返回
        if (heroNode != null) {
            return heroNode;
        }
        // 如果当前节点的右节点不为null 则向右递归查询
        if (this.right != null) {
            heroNode = this.right.preOrderSearch(no);
        }
        // 最后 返回
        return heroNode;
    }

    //todo　中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode heroNode = null;
        if (this.left != null) {
            heroNode = this.left.infixOrderSearch(no);
        }

        if (heroNode != null) {
            return heroNode;
        }

        System.out.println(this);

        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            heroNode = this.right.infixOrderSearch(no);
        }
        return heroNode;
    }

    //todo 后序遍历查询
    public HeroNode postOrderSearch(int no) {
        HeroNode heroNode = null;
        //向左遍历
        if (this.left != null) {
            heroNode = this.left.postOrderSearch(no);
        }
        //不为Null 则返回
        if (heroNode != null) {
            return heroNode;
        }
        //向右遍历
        if (this.right != null) {
            heroNode = this.right.postOrderSearch(no);
        }
        //不为null则返回
        if (heroNode != null) {
            return heroNode;
        }
        System.out.println(this);
        //比较当前节点
        if (this.no == no) {
            return this;
        }

        return heroNode;
    }

}
