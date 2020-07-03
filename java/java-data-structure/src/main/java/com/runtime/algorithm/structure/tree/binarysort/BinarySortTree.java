package com.runtime.algorithm.structure.tree.binarysort;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/2 18:01
 * @Description
 */
public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTreeSets treeSets = new BinarySortTreeSets();
        for (int i = 0; i < arr.length; i++) {
            treeSets.add(new Node(arr[i]));

        }
        treeSets.infixOrder();

       /* treeSets.delNode(7);
        treeSets.delNode(3);
        treeSets.delNode(5);
        treeSets.delNode(10);
        treeSets.delNode(1);
        treeSets.delNode(12);
        treeSets.delNode(9);*/


        System.out.println();
        treeSets.infixOrder();

    }
}


//创建二叉排序树
class BinarySortTreeSets {
    private Node root;

    //todo  查找要删除的节点
    public Node search(int value) {
        if (root != null) {
            return root.search(value);
        } else {
            return null;
        }
    }

    //todo 查看要删除的父节点
    public Node searchParent(int value) {
        if (root != null) {
            return root.searchParent(value);
        } else {
            return null;
        }
    }

    //todo  返回以Node 为根节点的二叉排序树的最小节点的值
    public int delRihtTreeMin(Node node) {
        Node target = node;
        //循环的查找左子节点，就会找到最小值
       /* while (target.left != null) {
            target = target.left;
        }*/
        while (target.right != null) {
            target = target.right;
        }

        //删除最小结点
        delNode(target.value);
        return target.value;
    }


    public void delNode(int value) {
        // 1. 找到要删除的节点
        Node targetNode = search(value);
        if (targetNode == null) {
            return;
        }
        // 2. 如果我们发现 当前这颗二叉树只有一个节点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        //3.去找targetNode 的父节点
        Node parent = searchParent(value);
      /*  if (parent == null) {
           return;
        }*/

        //todo 删除的是叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
            //todo 删除的是有两颗子树的节点
        } else if (targetNode.left != null && targetNode.right != null) {
            //int treeMin = delRihtTreeMin(targetNode.right);
            int treeMin = delRihtTreeMin(targetNode.left);
            targetNode.value = treeMin;
            //todo 删除的是有一颗字树的节点
        } else {
            // 删除节点的左节点不为空 (后续直接让父节点的左/右节点等于删除节点的左节点)
            if (targetNode.left != null) {
                if (parent != null) {
                    // 如果父节点的左节点就是要删除的值
                    if (parent.left.value == value) {
                        parent.left = targetNode.left;
                    } else { // 便让父节点的右节点等于删除节点的左节点
                        parent.right = targetNode.left;
                    }
                } else {
                    root = targetNode.left;
                }
            } else { //删除节点右节点不为空 (..... 等于 删除节点的 右节点)
                if (parent != null) {
                    // 如果父节点的左节点就是要删除的值
                    if (parent.left.value == value) {
                        parent.left = targetNode.right;
                    } else { //便让父节点的右节点等于删除节点的右节点
                        parent.right = targetNode.right;
                    }
                } else {
                    root = targetNode.right;
                }
            }

            /*// 判断父节点的下一个value是否是要删除的子节点
            if (parent.left.value == value) {
                //判断 子树是 左侧为叶子节点还是右侧为叶子节点
                if (targetNode.left != null) {
                    parent.left = targetNode.left;
                } else {
                    parent.left = targetNode.right;
                }
            } else {
                if (targetNode.left != null) {
                    parent.right = targetNode.left;
                } else {
                    parent.right = targetNode.right;
                }
            }*/

        }

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

    /**
     * 1. 确定当前节点是否是要删除的节点
     * 2. 如果不是 判断是大还是小 判断需要向左查询还是向右查询
     */

    //todo 查找要删除的节点
    public Node search(int value) {
        if (value == this.value) { //1.查看是否是当前节点
            return this;
        } else if (value < this.value) { //2.如果不是当前节点 但小于当前节点 那就继续向左查找
            if (this.left != null) {
                return this.left.search(value);//3.返回
            } else {
                return null;
            }
        } else { //那不小于 就是大于等于喽
            if (this.right != null) {
                return this.right.search(value);
            } else {
                return null;
            }
        }
    }

    /**
     * 1. 判断当前节点是不是要删除的父节点
     * 2. 判断是 小于还是大于  然后进行递归查询
     */
    //todo 查找要删除的父节点
    public Node searchParent(int value) {
        //如果当前结点就是要删除的结点的父结点，就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); //向左子树递归查找
            } else if (value > this.value && this.right != null) {
                return this.right.searchParent(value);  //向右子树递归查找
            } else {
                return null; // 没有找到父结点
            }
        }

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