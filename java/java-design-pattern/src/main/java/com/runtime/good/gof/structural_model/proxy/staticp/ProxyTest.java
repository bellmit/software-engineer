package com.runtime.good.gof.structural_model.proxy.staticp;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/16 8:37
 * @Description 代理模式的定义与特点
 *
 * ---- 优点: 代理模式在客户端与目标对象之间起到一个中介作用和保护目标对象的作用；代理对象可以扩展目标对象的功能；代理模式能将客户端与目标对象分离，在一定程度上降低了系统的耦合度；
 * --- 缺点: 在客户端和目标对象之间增加一个代理对象，会造成请求处理速度变慢；增加了系统的复杂度；
 * ----- 抽象主题   通过接口或抽象类声明真实主题和代理对象实现的业务方法。
 * 真实主题    实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
 * 代理类     提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。
 */
public class ProxyTest {

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.Request();
    }
}

//抽象主题
interface Subject {
    void Request();
}

//真实主题
class RealSubject implements Subject {
    @Override
    public void Request
            () {
        System.out.println("访问真实主题方法...");
    }
}

//代理
class Proxy implements Subject {
    private RealSubject realSubject;

    @Override
    public void Request() {

        if (realSubject == null) {
            realSubject = new RealSubject();
        }

        preRequest();
        realSubject.Request();
        postRequest();
    }

    public void preRequest() {
        System.out.println("访问真实主题之前的预处理。");
    }

    public void postRequest() {
        System.out.println("访问真实主题之后的后续处理。");
    }
}
