package com.runtime.good.gof.creationMode.cloneable;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/13 9:19
 * @Description
 */
public class PojoOne implements Cloneable {

    private String name;//引用类型

    private int age;//基本类型

    private Classes classes;//引用类型

    public PojoOne() {
    }

    @Override
    public String toString() {
        return "PojoOne{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", classes=" + classes +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public PojoOne(String name, int age, Classes classes) {
        this.name = name;
        this.age = age;
        this.classes = classes;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {

        PojoOne clone = (PojoOne) super.clone();
        clone.setClasses((Classes) classes.clone());
        return clone;
    }
}
