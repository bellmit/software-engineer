package com.runtime.good.gof.creation_mode.factory_mode;

import java.util.UUID;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/15 10:02
 * @Description
 */
public class T3_SimpleFactory {
    public static void main(String[] args) {
        XiaoMiFactoryT3 xiaoMiFactoryT3 = new XiaoMiFactoryT3();

        xiaoMiFactoryT3.makePC();
        xiaoMiFactoryT3.makePhone();

    }
}


interface PhoneT3 {
    void make();
}

interface PC {
    void make();
}

interface AbstractFactoryT3 {
    PhoneT3 makePhone();

    PC makePC();
}

class MinPhoneT3 implements PhoneT3 {

    public MinPhoneT3
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

class IphoneT3 implements PhoneT3 {
    public IphoneT3
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


class MiPC implements PC {
    public MiPC
            () {
        this.make();
    }

    @Override
    public void make() {
        // TODO Auto-generated method stub
        System.out.println("make xiaomi PC!");
    }
}

class MAC implements PC {
    public MAC() {
        this.make();
    }

    @Override
    public void make() {
        // TODO Auto-generated method stub
        System.out.println("make MAC!");
    }
}


class XiaoMiFactoryT3 implements AbstractFactoryT3 {
    @Override
    public PhoneT3 makePhone() {
        return new MinPhoneT3();
    }

    @Override
    public PC makePC() {
        return new MiPC();
    }
}

class AppleFactoryT3 implements AbstractFactoryT3 {
    @Override
    public PhoneT3 makePhone() {
        return new IphoneT3();
    }

    @Override
    public PC makePC() {
        return new MAC();
    }
}


class getMethod {
    public static Object getMethod(String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Object obj = Class.forName(str).newInstance();
        return obj;
    }

    public static void main(String[] args) throws Exception {
        // System.out.println(new MinPhoneT3().getClass().getName());
        AppleFactoryT3 method = (AppleFactoryT3) getMethod("com.runtime.good.gof.creation_mode.factory_mode.AppleFactoryT3");
        method.makePhone();
        method.makePC();

        method.makePC().make();

    }
}
