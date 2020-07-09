package com.runtime.algorithm.algorithm.divide_conquer;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/8 15:12
 * @Description 解决汉诺塔
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTowerMethod(3, 'A', 'B', 'C');
    }

    //todo 使用分治算法
    public static void hanoiTowerMethod(int num, char a, char b, char c) {
        System.out.println("--- " + a);

       // System.out.println("A -> " + a + "\t" + "B -> " + b + "\t" + " C -> " + c);
        if (num == 1) {
            System.out.println(a + " ---> " + c);
        } else {
            hanoiTowerMethod(num - 1, a, c, b);
            System.out.println(a + " ---> " + c);
            hanoiTowerMethod(num - 1, b, a, c);
        }
    }

}
