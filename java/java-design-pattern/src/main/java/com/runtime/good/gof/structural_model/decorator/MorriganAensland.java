package com.runtime.good.gof.structural_model.decorator;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/16 16:46
 * @Description
 */
public class MorriganAensland {
    public static void main(String[] args) throws InterruptedException {

        original o1 = new original();
        o1.display();
        Thread.sleep(3000);
        Succubus o2 = new Succubus(o1);
        o2.display();
        Thread.sleep(3000);
        Girl o3 = new Girl(o1);
        o3.display();

    }
}


//抽象构件角色：莫莉卡
interface Morrigan {
    public void display();
}

//具体构件角色：原身
class original extends JFrame implements Morrigan {

    private static final long serialVersionUID = 1L;
    private String t = "C:\\Users\\Px\\Desktop\\Morrigan0.jpg";

    public original() {
        super("《恶魔战士》中的莫莉卡·安斯兰");
    }

    public void setImage(String t) {
        this.t = t;
    }

    @Override
    public void display() {
        this.setLayout(new FlowLayout());
        JLabel l1 = new JLabel(new ImageIcon(t));
        this.add(l1);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

//抽象装饰角色：变形
class Changer implements Morrigan {
    public Morrigan m;

    public Changer(Morrigan m) {
        this.m = m;
    }

    @Override
    public void display() {
        m.display();
    }
}

//具体装饰角色：女妖
class Succubus extends Changer {
    public Succubus(Morrigan m) {
        super(m);
    }

    @Override
    public void display() {
        setChanger();
        super.display();
    }

    public void setChanger() {
        ((original) super.m).setImage("C:\\Users\\Px\\Desktop\\Morrigan1.jpg");
    }
}

//具体装饰角色：少女
class Girl extends Changer {
    public Girl(Morrigan m) {
        super(m);
    }

    @Override
    public void display() {
        setChanger();
        super.display();
    }

    public void setChanger() {
        ((original) super.m).setImage("C:\\Users\\Px\\Desktop\\Morrigan2.jpg");
    }
}