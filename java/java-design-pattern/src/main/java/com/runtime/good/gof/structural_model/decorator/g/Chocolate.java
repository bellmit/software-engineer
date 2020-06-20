package com.runtime.good.gof.structural_model.decorator.g;

//具体的Decorator,这里就是调味品
public class Chocolate extends Decorate {
    public Chocolate(Drink obj) {
        super(obj);
        setDes(" 巧克力 ");
        setPrice(3.0f);//调味品的价格
    }
}
