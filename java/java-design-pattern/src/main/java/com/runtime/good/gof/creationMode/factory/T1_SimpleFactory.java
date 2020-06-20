package com.runtime.good.gof.creationMode.factory;

import java.util.UUID;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/13 15:53
 * @Description 简单工厂模式
 * @URI https://www.cnblogs.com/yssjun/p/11102162.html
 */


public class T1_SimpleFactory {
    public static void main(String[] args) {
        PhoneFactory factory = new PhoneFactory();

        //System.out.println(new PhoneFactory.Iphone().getClass().);

        factory.makePhone("MiPhone");            // make xiaomi phone!
        //factory.makePhone("iPhone");

        PhoneFactory.Phone phone = factory.makePhone("MiPhone");

        System.out.println(phone instanceof PhoneFactory);


    }
}

class PhoneFactory {

    public Phone makePhone(String phoneType) {
        //if ()
        if ("MiPhone".equalsIgnoreCase(phoneType)) {
            return new MinPhone();
        } else if ("iPhone".equalsIgnoreCase(phoneType)) {
            return new Iphone();
        }
        return null;
    }


    interface Phone {
        void make();
    }

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
}
