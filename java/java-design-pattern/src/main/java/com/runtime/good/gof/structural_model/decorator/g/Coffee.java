package com.runtime.good.gof.structural_model.decorator.g;

//具体构建
public class Coffee extends Drink {
    @Override
    public float cost() {
        return super.getPrice();
    }
}
