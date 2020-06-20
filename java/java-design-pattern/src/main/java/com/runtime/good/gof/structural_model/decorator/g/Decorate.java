package com.runtime.good.gof.structural_model.decorator.g;

public class Decorate extends Drink {
    private Drink obj;

    public Decorate(Drink obj) {
        this.obj = obj;
    }

    @Override
    public float cost() {
        //getPrice  自己的价格
        System.out.println(super.getPrice());
        return super.getPrice() + obj.cost();
    }

    @Override
    public String getDes() {
        //return super.getDes();
        //obj.getDes()输出了被装饰着的信息
        return super.des + " " + super.getPrice() + "&&" + obj.getDes();
    }
}
