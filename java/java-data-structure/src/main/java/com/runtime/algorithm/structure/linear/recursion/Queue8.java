package com.runtime.algorithm.structure.linear.recursion;

import javax.crypto.MacSpi;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/25 13:50
 * @Description 8皇后问题
 */
public class Queue8 {

    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法", count);
        System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w
    }

    //todo 放置n 皇后
    private void check(int n) {
        // 如果放置第9个皇后 直接返回
        if (n == max) {
            print();
            return;
        }
        //依次放入皇后,判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前皇后 n 放到该行的第1列
            array[n] = i;
            //判断当放置n个皇后i列时,是否冲突
            if (judge(n)) {
                check(n + 1);
            }

        }

    }

    //todo 查看我们放置第N个皇后,就去检测当前皇后是否与前面已摆放的皇后冲突
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //判断是否和i皇后在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //todo 处处摆放的位置
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "");
        }
        System.out.println();
    }
}
