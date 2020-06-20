package com.runtime.good.gof.creation_mode.factory_mode;

import javax.swing.*;
import java.awt.*;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/13 10:48
 * @Description
 */
public class AnimalFarmTest {

    public static void main(String[] args) {
        try {
            Animal a;
            AnimalFarm af;
            af = (AnimalFarm) ReadXML2.getObject();
            a = af.newAnimal();
            a.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

//抽象产品：动物类
interface Animal {
    public void show();
}

//具体产品：马类
class Horse implements Animal {

    JScrollPane sp;
    JFrame jf = new JFrame("工厂方法模式测试");

    public Horse() {
        Container contentPane = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("动物：马"));
        sp = new JScrollPane(p1);
        contentPane.add(sp, BorderLayout.CENTER);
        JLabel l1 = new JLabel(new ImageIcon("C:\\Users\\Px\\Downloads\\3-1Q114140526\\A_Horse.jpg"));
        p1.add(l1);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //用户点击窗口关闭
    }

    @Override
    public void show() {
        jf.setVisible(true);
    }
}

class Cattle implements Animal {
    JScrollPane sp;
    JFrame jf = new JFrame("工厂方法模式测试");

    public Cattle() {
        Container contentPane = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("动物：牛"));
        sp = new JScrollPane(p1);
        contentPane.add(sp, BorderLayout.CENTER);
        JLabel l1 = new JLabel(new ImageIcon("C:\\Users\\Px\\Downloads\\3-1Q114140526\\A_Cattle.jpg"));
        p1.add(l1);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //用户点击窗口关闭
    }

    @Override
    public void show() {
        jf.setVisible(true);
    }
}

//抽象工厂：畜牧场
interface AnimalFarm {
    public Animal newAnimal();
}

class HorseFarm implements AnimalFarm {
    @Override
    public Animal newAnimal() {
        System.out.println("新马出生！");
        return new Horse();
    }
}

//具体工厂：养牛场
class CattleFarm implements AnimalFarm {
    @Override
    public Animal newAnimal() {
        System.out.println("新牛出生！");
        return new Cattle();
    }
}

class ReadXML2 {
    public static Object getObject() {
        try {
//            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = dFactory.newDocumentBuilder();
//            Document doc;
//            doc = builder.parse(new File("C:\\Users\\Px\\Downloads\\3-1Q114140526\\config2.xml"));
//            NodeList nl = doc.getElementsByTagName("className");
//            Node classNode = nl.item(0).getFirstChild();
//            String cName = "com.runtime.good.gof.createmode.FactoryMethod." + classNode.getNodeValue();
//            System.out.println("新类名：" + cName);

            //com.runtime.good.gof.createmode.FactoryMethod.CattleFarm

            //String name = CattleFarm.class.getName();
            String name = HorseFarm.class.getName();

            //aClass(name).newInstance();


            Class<?> c = Class.forName(name);
            Object obj = c.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Class<?> aClass(String name) throws ClassNotFoundException {
        return Class.forName(name);
    }
}
