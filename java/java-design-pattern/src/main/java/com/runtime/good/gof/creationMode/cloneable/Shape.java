package com.runtime.good.gof.creationMode.cloneable;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/13 9:39
 * @Description
 */
interface Shape extends Cloneable {
    public Object clone();

    public void countArea();
}

class Circle implements Shape {

    @Override
    public Object clone() {
        Circle w = null;
        try {
            w = (Circle) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("拷贝圆失败!");
        }
        return w;
    }

    @Override
    public void countArea() {
        {
            int r = 0;
            System.out.print("这是一个圆，请输入圆的半径：");
            Scanner input = new Scanner(System.in);
            r = input.nextInt();
            System.out.println("该圆的面积=" + 3.1415 * r * r + "\n");
        }
    }
}

class Square implements Shape {

    @Override
    public Object clone() {
        Square b = null;
        try {
            b = (Square) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("拷贝正方形失败!");
        }
        return b;

    }

    @Override
    public void countArea() {
        {
            int a = 0;
            System.out.print("这是一个正方形，请输入它的边长：");
            Scanner input = new Scanner(System.in);
            a = input.nextInt();
            System.out.println("该正方形的面积=" + a * a + "\n");
        }

    }
}


class ProtoTypeManager {
    private HashMap<String, Shape> ht = new HashMap<String, Shape>();

    public ProtoTypeManager() {
        ht.put("Circle", new Circle());
        ht.put("Square", new Square());
    }

    public void addshape(String key, Shape obj) {
        ht.put(key, obj);
    }

    public Shape getShape(String key) {
        Shape temp = ht.get(key);
        return (Shape) temp.clone();
    }
}

class ProtoTypeShape {
    public static void main(String[] args) {

        ProtoTypeManager pm = new ProtoTypeManager();

        Circle circle = (Circle) pm.getShape("Circle");
        circle.countArea();

        Square square = (Square) pm.getShape("Square");
        square.countArea();


//        pm.addshape("Null", null);
//
//        System.out.println(pm.toString());

    }

}