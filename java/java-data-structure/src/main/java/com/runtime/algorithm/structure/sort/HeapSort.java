package com.runtime.algorithm.structure.sort;

import java.util.Arrays;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/30 15:50
 * @Description 堆排序
 */
public class HeapSort {

    // 完成乱序数组 堆排序的方法
    //1. 构建大顶堆 2. 将大顶堆的值沉到数组末尾 3. 重新调整结构 继续构建 + 交换
    public static void HeapSortMethod(int arr[]) {
        int temp = 0;

        //todo 无需队列,构建成一个堆,根据升序选择最大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapSort(arr, i, arr.length);
        }

        System.out.println("构建大顶堆 ");
        System.out.println(Arrays.toString(arr));

        //todo : 将堆顶的元素与末尾的元素交换,将最大值沉到数组末尾
        //todo : 重新调整结构,使其满足堆定义,然后继续交换堆顶元素与当前末尾元素
        System.out.println("start");
        for (int i = arr.length - 1; i > 0; i--) {
            //交换
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapSort(arr, 0, i);

            //查看每次更改后的数组
            System.out.println(Arrays.toString(arr));
        }
    }

    // 接收父节点的索引/判断父节点 *2+1 与*2+2 哪个值大

    /**
     * @param arr 数组
     * @param i 非叶子节点的索引
     * @param length 数组的长度 [是变动的]
     */
    public static void heapSort(int arr[], int i, int length) {
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //1. k = i * 2 + 1 k 是 i结点的左子结点
        //todo 开始调整 求出当前节点的 [左节点] 与 [右节点]
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {

            if (k + 1 < length && arr[k] < arr[k + 1]) { //todo 说明左子结点的值小于右子结点的值
                k++; // k 指向右子结点
            }
            //todo 如果当前值 大于 非叶子节点 则进行赋值操作
            if (arr[k] > temp) { //如果子结点大于父结点
                arr[i] = arr[k]; //把较大的值赋给当前结点
                i = k; //!!! i 指向 k,继续循环比较
            } else {
                break;//非叶子节点 (从左至右 从上个至下)
            }
        }
        //todo 循环结束后 我们已经将i 为父节点的树的最大值 放在最顶部
        arr[i] = temp;//将temp值放到调整后的位置
    }

    // 测试 在  ALLSortTestMain 方法中

}
