package com.runtime.good.gof.structural_model.decorator.g;

public class Soy extends Decorate {
    public Soy(Drink obj) {
        super(obj);
        setDes("豆浆");
        setPrice(1.5f);
    }
}
