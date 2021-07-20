package com.runtime.java.collector.other;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/2/27 13:25
 * @Description
 */
public class Grow {
    public static void main(String[] args) {

        final Object[] objects = {1, 2, 3, 4, 5, 6, 7};

        System.out.println(new VectorGrow(objects, 0).elementData.length);
        System.out.println(new ArrayListGrow(objects, 0).elementData.length);


    }


    static class VectorGrow {
        private Object[] elementData;
        private int capacityIncrement;

        private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

        public VectorGrow(Object[] elementData, int capacityIncrement) {
            this.elementData = elementData;
            this.capacityIncrement = capacityIncrement;
            grow(elementData.length + 1);
        }

        private void grow(int minCapacity) {
            // overflow-conscious code // 目前元素个数
            int oldCapacity = elementData.length;

            // 第一种情况: 非定量扩容  0 > 0 = false 则 oldCapacity + oldCapacity = 2倍
            // 第二种情况: 定量扩容 oldCapacity + capacityIncrement
            int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                    capacityIncrement : oldCapacity);
            // 边界值判断 新长度 - 当前Vector元素个数 + 1  (边界值判断 计划扩容后的长度还没扩容前的长度多) 那就扩容 1 倍  (这 + 1  代表的是当前待插入的元素)
            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity;

            // 边界值判断 新长度 >  (Integer.MAX_VALUE - 8 = 2147483639) 则
            if (newCapacity - MAX_ARRAY_SIZE > 0)
                newCapacity = hugeCapacity(minCapacity);

            // 扩容
            elementData = Arrays.copyOf(elementData, newCapacity);
        }

        private static int hugeCapacity(int minCapacity) {
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();

            // 比 Integer.MAX_VALUE - 8 还大的长度 ? 采用 Integer 最大值 : 不然就采用 Integer.MAX_VALUE - 8
            return (minCapacity > MAX_ARRAY_SIZE) ?
                    Integer.MAX_VALUE :
                    MAX_ARRAY_SIZE;
        }
    }

    static class ArrayListGrow {

        private Object[] elementData;

        private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

        public ArrayListGrow(Object[] elementData, int capacityIncrement) {
            this.elementData = elementData;
            grow(elementData.length + 1);
        }


        private void grow(int minCapacity) {
            // overflow-conscious code // 当前数组中元素个数
            int oldCapacity = elementData.length;
            // 原有长度 >> 1 位 扩容 0.5 倍
            int newCapacity = oldCapacity + (oldCapacity >> 1);

            if (newCapacity - minCapacity < 0)
                newCapacity = minCapacity;

            if (newCapacity - MAX_ARRAY_SIZE > 0)
                newCapacity = hugeCapacity(minCapacity);
            // minCapacity is usually close to size, so this is a win:
            elementData = Arrays.copyOf(elementData, newCapacity);
        }

        private static int hugeCapacity(int minCapacity) {
            if (minCapacity < 0) // overflow
                throw new OutOfMemoryError();
            return (minCapacity > MAX_ARRAY_SIZE) ?
                    Integer.MAX_VALUE :
                    MAX_ARRAY_SIZE;
        }
    }

}
