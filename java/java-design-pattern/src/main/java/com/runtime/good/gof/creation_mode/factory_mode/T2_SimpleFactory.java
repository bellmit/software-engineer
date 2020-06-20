package com.runtime.good.gof.creation_mode.factory_mode;

import java.util.UUID;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/13 16:13
 * @Description 工厂方法模式(Factory Method)
 */
public class T2_SimpleFactory {
    public static void main(String[] args) {
        XiaoMiFactory xiaoMiFactory = new XiaoMiFactory();
        AppleFactory appleFactory = new AppleFactory();

        System.out.println();
        xiaoMiFactory.makePhone();
        appleFactory.makePhone();

        // ------------------
        AbstractFactoryI abstractFactoryI = new TestFactory();
        abstractFactoryI.makePhone();


    }
}

// 抽象实现
interface Phone {
    void make();
}

// 抽象工厂
interface AbstractFactoryI {
    Phone makePhone();
}

//具体工厂
class XiaoMiFactory implements AbstractFactoryI {
    @Override
    public Phone makePhone() {
        return new MinPhone();
    }

}
// 具体工厂
class AppleFactory implements AbstractFactoryI {
    @Override
    public Phone makePhone() {
        return new Iphone();
    }
}
// 具体工厂
class TestFactory implements AbstractFactoryI {
    @Override
    public Phone makePhone() {
        return new TestPhone();
    }
}

//具体产品
class MinPhone implements Phone {

    public MinPhone
            () {
        this.make();
    }

    @Override
    public void make
            () {
        // TODO Auto-generated method stub
        System.out.println("make xiaomi phone!");
    }
}
//具体产品
class Iphone implements Phone {
    public Iphone
            () {
        this.make();
    }

    public final String DVC = "UUID : =>" + UUID.randomUUID();

    @Override
    public void make
            () {
        // TODO Auto-generated method stub
        System.out.println("make iphone!");
    }
}
//具体产品
class TestPhone implements Phone {

    public TestPhone
            () {
        this.make();
    }

    @Override
    public void make() {
        System.out.println("TestPhone 初始化 Success");
    }
}
