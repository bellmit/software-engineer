package com.runtime.good.gof.creation_mode.cloneable;

import java.util.UUID;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/13 9:25
 * @Description
 */
public class TestClone {
    public static void main(String[] args) {
        PojoOne one = new PojoOne();
        one.setAge(20);
        one.setName("TT1");

        Classes classes = new Classes(123, "好个娃儿 可惜道破");
        one.setClasses(classes);


        try {

            PojoOne clonePojo = (PojoOne) one.clone();

            System.out.println("clonePojo ==> " + clonePojo);

            System.out.println("one ==> " + one);

            System.out.println(UUID.randomUUID());

            clonePojo.setAge(30);
            clonePojo.setName("TT2");
            //clonePojo.setClasses(new Classes(234, "可惜道破"));
            clonePojo.getClasses().setClassId(234);
            clonePojo.getClasses().setClassName("可惜道破");

            System.out.println("clonePojo ==> " + clonePojo);
            System.out.println("one ==> " + one);


        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
