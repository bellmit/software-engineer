package com.runtime.good.gof.structural_model.adapter.classAdapter;
//被适配的类 适配者类
public class Voltage220V {
    //输出220V的电压
    public int output220V(){
        int src=220;
        System.out.println("电压="+src+"伏");
        return src;
    }
}
