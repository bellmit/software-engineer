package com.runtime.good.gof.principle;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/13 14:27
 * @Description 依赖倒置原则的定义
 *  --- 层模块不应该依赖低层模块，两者都应该依赖其抽象；抽象不应该依赖细节，细节应该依赖抽象
 *      --- 要面向接口编程，不要面向实现编程
 *      --- 使用接口或者抽象类的目的是制定好规范和契约，而不去涉及任何具体的操作，把展现细节的任务交给它们的实现类去完成。
 */
public class 依赖倒置原则 {
    public static void main(String[] args) {

        Customer wang = new Customer();
        System.out.println("顾客购买以下商品：");
        wang.shopping(new WuyuanShop());
        wang.shopping(new ShaoguanShop());

    }
}


//商店
interface Shop {
    String sell();
}

//韶关网店
class ShaoguanShop implements Shop {
    @Override
    public String sell() {
        return "韶关土特产：香菇、木耳……";
    }
}

//婺源网店
class WuyuanShop implements Shop {
    @Override
    public String sell() {
        return "婺源土特产：绿茶、酒糟鱼……";
    }
}

//顾客
class Customer {
    public void shopping(Shop shop) {
        //购物
        System.out.println(shop.sell());
    }
}