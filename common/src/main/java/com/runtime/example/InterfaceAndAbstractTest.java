package com.runtime.example;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/3/5 9:21
 * @Description
 */

/**
 * 抽象类 implements 接口 可以不实现接口内方法
 * 抽象类 抽象方法 可以不实现交给子类实现
 */
public class InterfaceAndAbstractTest extends AbstractAbc {
    @Override
    public int ov1(int a, int b) {
        return 0;
    }

    @Override
    int ov2(String str) {
        return super.ov2(str);
    }

    @Override
    int ov3(String strc) {
        return 0;
    }
}


@FunctionalInterface
interface InterfaceAbc {
    default void over() {
        System.out.println("start");
    }

    int ov1(int a, int b);
}

abstract class AbstractAbc implements InterfaceAbc {
    int ov2(String str) {
        return str.length();
    }

    abstract int ov3(String strc);
}

class test {
    public static void main(String[] args) {
        InterfaceAndAbstractTest interfaceAndAbstractTest = new InterfaceAndAbstractTest();
        interfaceAndAbstractTest.ov1(1, 23);
        interfaceAndAbstractTest.ov2("123");
    }
}
