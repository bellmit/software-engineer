package com.runtime.java.collections.hashmap;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/2/26 9:05
 * @Description
 */
public class HashMapTest<K, V> {
    // 自定义 数组

    static HashEntry[] list = new HashEntry[8];

    public static void main(String[] args) {


//        // 填充数据  put
//        HashEntry<String, String> entry = new HashEntry<>();
//
//        entry.put("1", "2");
//        // 获取数据 get
//        System.out.println(entry.get("1"));


        CustomMyHashMap<String, String> map = new CustomMyHashMap<>();
        System.out.println(map.put("a", "张三"));
        System.out.println(map.put("b", "张三1"));
        System.out.println(map.put("c", "张三1"));
        System.out.println(map.put("97", "张三1"));

        map.show();

    }


    /**
     * 自定义 Entry 载体
     *
     * @param <K>
     * @param <V>
     */
    static class HashEntry<K, V> {
        K k;
        V v;

        public HashEntry() {
        }

        public V get(K k) {
            int index = getIndex();
            return (V) list[index].get(k);
        }

        public void put(K k, V v) {
            // 先计算出 索引位置
            int index = getIndex();
            if (list[index] == null) {
                list[index].put(k, v);
            }
        }

        public int getIndex() {
            return k.hashCode() % list.length;
        }
    }


    private static class CustomMyHashMap<K, V> {

        private int size = 8;
        private int number = 0; // 存储节点的个数
        private ArrayList<LinkedList<Node<K, V>>> array_head = new ArrayList<>(size);

        private CustomMyHashMap() {
            for (int i = 0; i < size; i++) {
                LinkedList<Node<K, V>> list = new LinkedList<Node<K, V>>();// 哈希数组中初始化存储的为空链表头
                array_head.add(list);// 初始化的时候就将空节点头添加到数组中去
            }
        }

        public V put(K k, V v) {
            V putVal = putVal(k, v);
            return putVal;
        }

        public V get(K k) {
            return getVal(k);
        }

        private V putVal(K k, V v) {

            // 如果存在 Key 则替换
            if (containsKey(k)) return replace(k, v);

            // 不存在 则添加
            int index = getIndex(k);

            Node<K, V> node = new Node<>(k, v, null);
            LinkedList<Node<K, V>> list = array_head.get(index);

            list.add(node);
            number++;

            return node.getValue();
        }

        private V getVal(K k) {
            int index = getIndex(k);
            LinkedList<Node<K, V>> list = array_head.get(index);

            for (int i = 0; i < list.size(); i++) {

                if (accuracy(list.get(i), k)) {
                    return list.get(i).getValue();
                }
            }
            return null;
        }


        /**
         * 是否有 Hash 冲突
         * - 有: 获取 index 遍历链表
         * - 没有: false
         *
         * @param k
         * @return
         */
        private boolean containsKey(K k) {

            int index = getIndex(k);
            LinkedList<Node<K, V>> list = array_head.get(index); // O (1)

            for (int i = 0; i < list.size(); i++) { // O (n)
                Node<K, V> node = list.get(i);
                if (accuracy(node, k)) return true;
                //node = node.next;
            }
            return false;
        }

        /**
         * 打印哈希表
         */
        public void show() {
            System.out.println("打印哈希表");
            for (int i = 0; i < size; i++) {
                LinkedList<Node<K, V>> nodes = array_head.get(i);
                System.out.println("链表: " + i);
                for (int j = 0; j < nodes.size(); j++) {
                    Node<K, V> node = nodes.get(j);
                    System.out.println("节点" + (j + 1) + ":" + "<" + node.getKey() + "," + node.getValue() + ">" + "  ");
                }
                System.out.println();
            }
        }


        private V replace(K k, V v) {
            V tempHistory = null;

            int index = getIndex(k);
            LinkedList<Node<K, V>> list = array_head.get(index);
            for (int i = 0; i < list.size(); i++) {
                Node<K, V> node = list.get(i);

                if (accuracy(node, k)) {
                    tempHistory = node.getValue();
                    node.setValue(v);
                    return tempHistory;
                }
            }
            return null;
        }

        /**
         * 抽象比较 equals 与 ==
         *
         * @param node
         * @param k
         * @return
         */
        private boolean accuracy(Node<K, V> node, K k) {
            if ((node != null && node.key.equals(k)) || node.key == k) {
                return true;
            }
            return false;
        }

        /**
         * 计算字符串的哈希码 ASCII码相加
         *
         * @param s
         * @return
         */
        private int hashcode(String s) {
            int k = 0;
            for (int i = 0; i < s.length(); i++) {
                k += s.charAt(i);
            }
            return k;
        }

        /**
         * 得到哈希码对应在数组中的位置
         *
         * @param k
         * @return
         */
        private int locate(int k) {
            int m = k % size;
            return m;
        }

        /**
         * 根据 k 计算 hashCode 并 返回对应数组 index
         *
         * @param k
         * @return
         */
        private int getIndex(K k) {
//            int code = hashcode(k.toString());

            //return locate(hashcode(k.toString()));
            return locate(k.hashCode());
        }


        /**
         * return Map . size
         *
         * @return
         */
        private int getSize() {
            return number;
        }

        private class Node<K, V> {
            private K key;
            private V value;
            private Node<K, V> next;

            public Node() {
            }

            public K getKey() {
                return key;
            }

            public void setKey(K key) {
                this.key = key;
            }

            public V getValue() {
                return value;
            }

            public void setValue(V value) {
                this.value = value;
            }

            public Node getNext() {
                return next;
            }

            public void setNext(Node next) {
                this.next = next;
            }

            public Node(K key, V value, Node next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }
    }
}