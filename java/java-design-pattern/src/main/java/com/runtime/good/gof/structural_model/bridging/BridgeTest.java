package com.runtime.good.gof.structural_model.bridging;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/16 10:47
 * @Description 桥接模式
 * 抽象化（Abstraction）角色：定义抽象类，并包含一个对实现化对象的引用。
 * 扩展抽象化（Refined    Abstraction）角色：是抽象化角色的子类，实现父类中的业务方法，并通过组合关系调用实现化角色中的业务方法。
 * 实现化（Implementor）角色：定义实现化角色的接口，供扩展抽象化角色调用。
 * 具体实现化（Concrete Implementor）角色：给出实现化角色接口的具体实现。
 */
public class BridgeTest {
    public static void main(String[] args) {
        Implementor imple = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(imple);
        abs.Operation();
    }
}


//抽象化角色
abstract class Abstraction {
    protected Implementor imple;

    protected Abstraction(Implementor imple) {
        this.imple = imple;
    }

    public abstract void Operation();
}


//具体实现化角色
class ConcreteImplementorA implements Implementor {
    @Override
    public void OperationImpl() {
        System.out.println("具体实现化(Concrete Implementor)角色被访问");
    }
}

//实现化角色
interface Implementor {
    public void OperationImpl();
}

//扩展抽象化角色
class RefinedAbstraction extends Abstraction {
    protected RefinedAbstraction(Implementor imple) {
        super(imple);
    }

    @Override
    public void Operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
        imple.OperationImpl();
    }
}
