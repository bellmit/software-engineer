package com.runtime.java.test;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/7 17:47
 * @Description
 */

class Test {
    public Test() {
        Inner s1 = new Inner();
        s1.a = 10;
        Inner s2 = new Inner();
        s2.a = 20;
        //System.out.println(s1.a == new Test().Inner.a);
        Test.Inner s3 = new Test.Inner();
        System.out.println(s3.a);
    }

    class Inner {
        public int a = 5;
    }

    public static void main(String[] args) {
        Test t = new Test();
        Inner r = t.new Inner();
        System.out.println(r.a);
    }
}