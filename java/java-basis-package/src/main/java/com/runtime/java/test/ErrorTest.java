package com.runtime.java.test;

import javax.xml.transform.Source;
import java.sql.SQLOutput;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/7 16:42
 * @Description
 */
public class ErrorTest {
    public static void main(String[] args) {
        E1 e1 = new E1();

        try {
            e1.method(1);
        } catch (Exception e) {
            e1.method2(0 + "\t" + e.getMessage());
            //e.printStackTrace();
        }

        //String

    }


}

class E1 {
    public static void method(int num) /*throws Exception*/ {
        System.out.println(num / 0);
    }

    public static void method2(String num) {
        System.out.println(num);
    }

}

interface a1 {
    void test();
}

interface a2 extends a1 {
    void test();
}

abstract class a3 implements a1 {
    @Override
    public void test() {

    }
}

class a4 {
    void tst() {
    }
}

class a5 {
    public static void main(String[] args) {
        //throw new Exception();


        // Other o = new Other(i);
        //new a5().addOne(o);
    }

    public void addOne(final Other o) {
        //o = new Other();
        //o.i++;
    }
}

class Other {
    public final int i;

    Other(int i) {
        this.i = i;
    }
}


interface A {
    int x = 0;
}

class B {
    int x = 1;
}

class C extends B implements A {

    public void pX() {
        System.out.println(super.x);
    }

    public static void main(String[] args) {
        new C().pX();
    }
}

class ddd implements A{
    public void main(String[] args) {
        //System.out.println(super.x);
    }
}