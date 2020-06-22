package com.runtime.good.gof.structural_model.decorator.g;

public class Client {
    public static void main(String[] args) {
        //装饰者模式下订单,2份巧克力+1份牛奶的LongBlack

//        //1.点一份LongBlack
//        Drink order=new LongBlack();
//        System.out.println(order.cost());
//        System.out.println(order.getDes());
//        //2.order加入一份牛奶
//        order = new Milk(order);
//        System.out.println(order.cost());
//        System.out.println(order.getDes());
//        //3.order加入一份巧克力
//        order = new Chocolate(order);
//        System.out.println(order.cost());
//        System.out.println(order.getDes());
//        //4.order再加入一份巧克力
//        order = new Chocolate(order);
//        System.out.println(order.cost());
//        System.out.println(order.getDes());

        // 抽象构建 具体构建 抽象装饰 具体装饰
        Drink black = new ShortBlack();

        black = new Milk(black);

        black = new Soy(black);
        System.out.println(black.cost());
        System.out.println(black.getDes());


    }
    //structure
}
