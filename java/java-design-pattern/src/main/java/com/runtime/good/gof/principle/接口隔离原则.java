package com.runtime.good.gof.principle;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/19 22:25
 * @Description 接口隔离原则
 * --- 程序员尽量将臃肿庞大的接口拆分成更小的和更具体的接口，让接口中只包含客户感兴趣的方法。
 */
public class Jie_kou_ge_li {
    public static void main(String[] args) {
        InputModule input = StuScoreList.getInputModule();
        CountModule count = StuScoreList.getCountModule();
        PrintModule print = StuScoreList.getPrintModule();
        input.insert();
        count.countTotalScore();
        print.printStuInfo();
        //print.delete();
    }
}

//输入模块接口
interface InputModule {
    void insert();

    void delete();

    void modify();
}

//统计模块接口
interface CountModule {
    void countTotalScore();

    void countAverage();
}

//打印模块接口
interface PrintModule {
    void printStuInfo();

    void queryStuInfo();
}

//实现类
class StuScoreList implements InputModule, CountModule, PrintModule {
    private StuScoreList() {
    }

    public static InputModule getInputModule() {
        return (InputModule) new StuScoreList();
    }

    public static CountModule getCountModule() {
        return (CountModule) new StuScoreList();
    }

    public static PrintModule getPrintModule() {
        return (PrintModule) new StuScoreList();
    }

    @Override
    public void insert() {
        System.out.println("输入模块的insert()方法被调用！");
    }

    @Override
    public void delete() {
        System.out.println("输入模块的delete()方法被调用！");
    }

    @Override
    public void modify() {
        System.out.println("输入模块的modify()方法被调用！");
    }

    @Override
    public void countTotalScore() {
        System.out.println("统计模块的countTotalScore()方法被调用！");
    }

    @Override
    public void countAverage() {
        System.out.println("统计模块的countAverage()方法被调用！");
    }

    @Override
    public void printStuInfo() {
        System.out.println("打印模块的printStuInfo()方法被调用！");
    }

    @Override
    public void queryStuInfo() {
        System.out.println("打印模块的queryStuInfo()方法被调用！");
    }
}
