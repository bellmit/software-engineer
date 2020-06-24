package com.runtime.algorithm.structure.linear.recursion;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/24 21:03
 * @Description 递归测试
 */
public class RecursionTest {

    public static void main(String[] args) {
        // test(4);
        System.out.println(factorial(6));
        ;
    }

    public static void test(int num) {
        if (num > 2) {
            //System.out.println(num);
            //test(num -1 );//2 3 4
            test(--num); // 3  2
        }
        System.out.printf("%d 出现次数\n", num);
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
// 5 4 3 2  2x3x4x5


