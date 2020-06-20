package com.runtime.good.gof.structural_model.adapter.classAdapter;
//适配器类 目标适配器类
public class VoltageAdapter extends Voltage220V implements Voltage5V {

    @Override
    public int output5V() {
        //获取到220V电压
        int srcV=output220V();
        int dstV=srcV/44;//转成5V
        return dstV;
    }
}