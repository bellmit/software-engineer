package com.runtime.java.juc;

import java.util.ArrayList;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/28 15:20
 * @Description
 */
public class LambdaExpressDemo2 {
    public static void main(String[] args) {


        LambdaTest lambda = (int a, int b) -> {
            System.out.println(a + "\t" + b);
            return a + b;
        };

        System.out.println(lambda.testMethod2(1, 2));

        System.out.println(lambda.testMethod3(0, 8));
        ;

        System.out.println(LambdaTest.testMethod4(10, 2));
        ;



    }
}


@FunctionalInterface
interface LambdaTest {
    //void testMethod1(int a, int b);

    int testMethod2(int a, int b);

    default int testMethod3(int a, int b) {
        return a * b;
    }

    static int testMethod4(int a, int b) {
        return a / b;
    }

}
