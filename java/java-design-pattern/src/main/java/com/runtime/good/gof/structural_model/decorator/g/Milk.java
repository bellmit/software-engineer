package com.runtime.good.gof.structural_model.decorator.g;

public class Milk extends Decorate {
    public Milk(Drink obj) {
        super(obj);
        setDes("牛奶");
        setPrice(2.0f);
    }
}
