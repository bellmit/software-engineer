package com.runtime.java.jvm;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/1 11:17
 * @Description
 */
public class NewJNI {
    {
        System.load("D:\\SNI.dll");
    }
    //C:\PxSoftwareEngineer\java\java-basis-package\src\main\java\com\runtime\java\jvm\NewJNI.java
    public native void sayHelloWordJNI();

    public static void main(String[] args) {
        new NewJNI().sayHelloWordJNI();
    }


}
