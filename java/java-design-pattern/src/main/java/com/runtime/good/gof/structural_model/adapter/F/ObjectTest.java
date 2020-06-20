package com.runtime.good.gof.structural_model.adapter.F;

import java.util.Random;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/20 21:08
 * @Description
 */
public class ObjectTest {

    public static void main(String[] args) {
        System.out.println(new ObjectDDlClassTwo(new ObjectDDlOne()).number());
    }
}

//目标接口
interface ObjectDDl {
    int number();
}

//适配者类
class ObjectDDlOne {
    public int ObjectDDlOne1() {
        return 30;
    }
}

//适配器类
class ObjectDDlClass implements ObjectDDl {

    ObjectDDlOne objectDDlOne;

    public ObjectDDlClass(ObjectDDlOne objectDDlOne) {
        this.objectDDlOne = objectDDlOne;
    }

    @Override
    public int number() {
        return objectDDlOne.hashCode();
    }
}


class ObjectDDlClassTwo implements ObjectDDl {
    ObjectDDlOne objectDDlOne;

    public ObjectDDlClassTwo(ObjectDDlOne objectDDlOne) {
        this.objectDDlOne = objectDDlOne;
    }

    @Override
    public int number() {
        return new Random().nextInt(10);
    }
}
