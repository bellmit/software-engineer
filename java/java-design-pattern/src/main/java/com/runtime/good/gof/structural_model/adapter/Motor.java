package com.runtime.good.gof.structural_model.adapter;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/16 10:26
 * @Description
 */

//目标：发动机
public interface Motor {
    public void drive();
}

//适配者1：电能发动机
class ElectricMotor {
    public void electricDrive
            () {
        System.out.println("电能发动机驱动汽车！");
    }
}

//适配者2：光能发动机
class OpticalMotor {
    public void opticalDrive
            () {
        System.out.println("光能发动机驱动汽车！");
    }
}

//电能适配器
class OpticalAdapter implements Motor {
    private OpticalMotor motor;

    public OpticalAdapter
            () {
        motor = new OpticalMotor();
    }

    @Override
    public void drive() {
        motor.opticalDrive();
    }
}

//电能适配器
class ElectricAdapter implements Motor {
    private ElectricMotor emotor;


    public ElectricAdapter
            () {
        emotor = new ElectricMotor();
    }
    @Override
    public void drive
            () {
        emotor.electricDrive();
    }
}

class MotorAdapterTest {
    public static void main(String[] args) throws Exception {
        System.out.println("适配器模式测试：");


        String Path = "com.runtime.good.gof.structural_model.adapter.ElectricAdapter";
        String Path1 = "com.runtime.good.gof.structural_model.adapter.OpticalAdapter";
        Motor motor = (Motor) Class.forName(Path1).newInstance();
        motor.drive();
    }
}
