package com.runtime.java.jvm;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/31 15:03
 * @Description
 */
public class D1 {
    public static void main(String[] args) throws ClassNotFoundException {
        //long maxMemory = Runtime.getRuntime().maxMemory() ;//返回 Java 虚拟机试图使用的最大内存量。
        //long totalMemory = Runtime.getRuntime().totalMemory() ;//返回 Java 虚拟机中的内存总量。
        //System.out.println("MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
        //System.out.println("TOTAL_MEMORY = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 / 1024) + "MB");

       //System.out.println(D1.class.getClassLoader());
       //System.out.println(D1.class.getClassLoader().getParent());
       //System.out.println(D1.class.getClassLoader().getParent().getParent());

        new Thread().start();

        //System.out.println(D1.class.getClassLoader().loadClass("D1"));

        ClassLoader.getSystemClassLoader().loadClass("D1");


        //-Xms10m -Xmx10m -XX:+PrintGCDetails
        //int[] ints = new int[1024 * 1024 * 1024];


    }

}
