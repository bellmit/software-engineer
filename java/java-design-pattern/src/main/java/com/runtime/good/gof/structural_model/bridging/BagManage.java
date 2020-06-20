package com.runtime.good.gof.structural_model.bridging;

import javax.swing.*;
import java.awt.*;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/16 10:55
 * @Description 包 跟颜色
 */
public class BagManage {
    public static void main(String[] args) throws Exception {
        Color color;
        Bag bag;


        //color = (Color) Class.forName("com.runtime.good.gof.createmode.bridge.Yellow").newInstance();
        color = (Color) Class.forName("com.runtime.good.gof.structural_model.bridging.Red").newInstance();
        System.out.println(color);
        //bag = (Bag) Class.forName("com.runtime.good.gof.createmode.bridge.HandBag").newInstance();
        bag = (Bag) Class.forName("com.runtime.good.gof.structural_model.bridging.Wallet").newInstance();
        bag.setColor(color);
        String name = bag.getName();
        show(name);
    }

    public static void show(String name) {
        JFrame jf = new JFrame("桥接模式测试");
        Container contentPane = jf.getContentPane();
        JPanel p = new JPanel();
        JLabel l = new JLabel(new ImageIcon("src/bridge/" + name + ".jpg"));
        p.setLayout(new GridLayout(1, 1));
        p.setBorder(BorderFactory.createTitledBorder("女士皮包"));
        p.add(l);
        contentPane.add(p, BorderLayout.CENTER);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

//实现化角色：颜色
interface Color {
    String getColor();
}

//具体实现化角色：黄色
class Yellow implements Color {
    @Override
    public String getColor() {
        System.out.println("yellow");
        return "yellow";
    }
}

//具体实现化角色：红色
class Red implements Color {
    @Override
    public String getColor() {
        System.out.println("red");
        return "red";
    }
}

//抽象化角色：包
abstract class Bag {
    protected Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract String getName();
}


//扩展抽象化角色：挎包
class HandBag extends Bag {
    @Override
    public String getName() {
        System.out.println("HandBag");
        return color.getColor() + "HandBag";
    }
}

//扩展抽象化角色：钱包
class Wallet extends Bag {
    @Override
    public String getName() {
        System.out.println("Wallet");
        return color.getColor() + "Wallet";
    }
}