package com.runtime.java.reflection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/17 15:43
 * @Description
 */
public class GetInstance {

    public static void main(String[] args) {

        // 通过对象的class 获取  \\ ->> 若已知具体的类，通过类的class属性获取，该方法最为安全可靠，程序性能最高
        Class<Person> aClass1 = Person.class;
        System.out.println(aClass1);

        //获取运行中的class  \\ ->> 知某个类的实例，调用该实例的getClass()方法获取Class对象
        Class<? extends Person> aClass2 = new Person("1", 2).getClass();
        System.out.println(aClass2);

        //通过 getName 获取  \\ ->> 已知一个类的全类名，且该类在类路径下，可通过Class类的静态方法forName()获取，可能抛出ClassNotFoundException
        Class<?> aClass3 = null;
        try {
            aClass3 = Class.forName("com.runtime.java.reflection.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(aClass3);

        // 其他方式(不做要求)
        Class aClass4 = null;
        try {
            aClass4 = GetInstance.class.getClassLoader().loadClass("com.runtime.java.reflection.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println(aClass1 == aClass2);
        System.out.println(aClass1 == aClass3);
        System.out.println(aClass1 == aClass4);


        Properties prop = new Properties();
        ClassLoader loader = GetInstance.class.getClassLoader();
        InputStream asStream = loader.getResourceAsStream("classloader.properties");
        //InputStream stream = GetInstance.class.getClassLoader().getResourceAsStream("classloader.properties");
        try {
            prop.load(asStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(prop.getProperty("name"));
        System.out.println(prop.getProperty("age"));
    }


}
