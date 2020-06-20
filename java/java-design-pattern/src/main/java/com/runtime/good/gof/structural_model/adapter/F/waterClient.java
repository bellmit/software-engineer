package com.runtime.good.gof.structural_model.adapter.F;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/20 21:01
 * @Description
 */
public class waterClient {
    public static void main(String[] args) {
        waterAdapter1 adapter = new waterAdapter1();
        System.out.println(adapter.water());;
    }
}


// 目标接口
interface Water {
    String water();
}

//适配者类
class WaterDoctor {
    public String output220V() {
        System.out.println("水库有水");
        return "有水";
    }
}

//适配器类

class waterAdapter1 extends WaterDoctor implements Water {
    @Override
    public String water() {

        return output220V().concat("好多");
    }
}

class waterAdapter2 extends WaterDoctor implements Water {
    @Override
    public String water() {

        return output220V().concat("农夫山泉");
    }
}