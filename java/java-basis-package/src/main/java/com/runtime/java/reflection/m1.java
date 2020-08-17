package com.runtime.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/17 20:10
 * @Description
 */
public class m1 {
    public static void main(String[] args) throws Exception, InstantiationException, ClassNotFoundException, InvocationTargetException {
        //Person person = Person.class.newInstance();

//        Constructor constructors = Class.forName("com.runtime.java.reflection.Person").getConstructor(String.class, Integer.class);
//        Object o = constructors.newInstance("123", 20);
//        System.out.println(o);


        /***
         * -- public Class<?>[] getInterfaces() //确定此对象所表示的类或接口实现的接口
         * -- public Class<? Super T> getSuperclass() //返回表示此 Class 所表示的实体（类、接口、基本类型）的父类的Class
         * -- public Constructor<T>[] getConstructors() //返回此 Class 对象所表示的类的所有public构造方法
         * -- public Constructor<T>[] getDeclaredConstructors() //返回此 Class 对象表示的类声明的所有构造方法
         *
         * Constructor
         * -- 取得修饰符: public int getModifiers();
         * -- 取得方法名称: public String getName();
         * -- 取得参数的类型：public Class<?>[] getParameterTypes();
         * -- public Method[] getDeclaredMethods() //返回此Class对象所表示的类或接口的全部方法
         * -- public Method[] getMethods() //返回此Class对象所表示的类或接口的public的方法
         *
         * Method
         * -- public Class<?> getReturnType() 取得全部的返回值
         * -- public Class<?>[] getParameterTypes() 取得全部的参数
         * -- public int getModifiers() 取得修饰符
         * -- public Class<?>[] getExceptionTypes() 取得异常信息
         *
         * Field
         * -- public Field[] getFields()
         * -- public Field[] getDeclaredFields()
         * -- public int getModifiers() 以整数形式返回此Field的修饰符
         * -- public Class<?> getType() 得到Field的属性类型
         * -- public String getName() 返回Field的名称。

         */
    }
}
