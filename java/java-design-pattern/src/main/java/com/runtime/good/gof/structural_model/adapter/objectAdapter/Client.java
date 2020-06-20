package com.runtime.good.gof.structural_model.adapter.objectAdapter;
//合成复用原则
public class Client {
    public static void main(String[] args) {
        Phone phone=new Phone();
        phone.charging(new VoltageAdapter(new Voltage220V()));
    }
}
