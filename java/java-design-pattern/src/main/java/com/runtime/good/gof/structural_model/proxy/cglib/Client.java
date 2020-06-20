package com.runtime.good.gof.structural_model.proxy.cglib;


public class Client {
    public static void main(String[] args) {
        TeacherDao target = new TeacherDao();
        TeacherDao instance = (TeacherDao) new ProxyFactory(target).getProxyInstance();
        instance.teach();
    }
}
