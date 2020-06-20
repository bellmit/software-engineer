package com.runtime.good.gof.structural_model.proxy.dynamic;


public class Client {
    public static void main(String[] args) {
        TeacherDao teacherDao=new TeacherDao();
        ITeacherDao instance = (ITeacherDao) new ProxyFactory(teacherDao).getProxyInstance();
        instance.teach();
        instance.sayHello("java");
    }
}
