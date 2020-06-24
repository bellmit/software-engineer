package com.runtime.algorithm.structure.linear.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/24 15:40
 * @Description 逆波兰 表达式
 */
public class PolandNotation {

    public static void main(String[] args) {
        // System.out.println(toInfixExpressionList("10+((2+3)×4)-5"));
        System.out.println(parseSuffixExpresionList(toInfixExpressionList("10+((2+3)*4)-5")));

    }

    //todo 将 中缀表达式转换成对应的List
    public static List<String> toInfixExpressionList(String str) {
        //遍历字符串 需要 index
        List<String> list = new ArrayList<>();
        // 指针 用于遍历字符串
        int index = 0;
        // 获取到的每一个值
        char c = ' ';
        // 防止多位数 字符 出现
        String tempStr = "";

        do {
            //如果c是一个非数字，我需要加入到ls
            if ((c = str.charAt(index)) < 48 || (c = str.charAt(index)) > 57) {
                list.add("" + c);
                index++;
            } else {//如果是一个数，需要考虑多位数
                tempStr = "";
                while (index < str.length() && (c = str.charAt(index)) >= 48 && (c = str.charAt(index)) <= 57) {
                    tempStr += c;
                    index++;
                }
                list.add(tempStr);
            }
        } while (index < str.length());

        return list;//返回
    }

    //todo 完成对逆波兰表达式的运算
    public static int calculate(List<String> list) {

        //ArrayList<String> arrayList = new ArrayList<>();

        //todo 创建给栈, 只需要一个栈即可
        Stack<String> stack = new Stack<>();
        //todo 遍历 取出 数组中的值
        for (String everyStr : list) {
            // todo 这里使用正则表达式来取出数
            if (everyStr.matches("\\d+")) {
                stack.push(everyStr);
            } else { // 如果不是数字 是符号 则需要 判断 优先级
                //todo pop 出 两个数 并计算 在入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());

                int res = 0;

                if ("+".equals(everyStr)) {
                    res = num1 + num2;
                } else if ("-".equals(everyStr)) {
                    res = num1 - num2;
                } else if ("*".equals(everyStr)) {
                    res = num1 * num2;
                } else if ("/".equals(everyStr)) {
                    res = num1 / num2;
                } else {
                    System.err.printf("错误符号 %d", everyStr);
                }
                //todo 结果入栈
                stack.push("" + res);
            }

        }
        //todo 最后留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }

    //todo 将得到的中缀表达式对应的List => 后缀表达式对应的List
    public static List<String> parseSuffixExpresionList(List<String> list) {
        //todo 定义两个栈
        Stack<String> s1 = new Stack<>();
        ArrayList<String> arrayList = new ArrayList<>();
        //todo 遍历list
        for (String everyStr : list) {
            // todo 1 如果是一个值 就加入到 arrayList
            if (everyStr.matches("\\d+")) {
                arrayList.add(everyStr);
                // todo 2 如果是 ( 括号 直接压入栈中
            } else if ("(".equals(everyStr)) {
                s1.push(everyStr);
                // todo 3 如果是 ) 括号 则需要找出 对应的( 括号
            } else if (")".equals(everyStr)) {
                // 循环 判断顶端 的值 不为 (括号时 /// 则将栈中值弹出 并添加进 arrayList
                while (!"(".equals(s1.peek())) {
                    arrayList.add(s1.pop());
                } // todo 4 切记 弹栈 操作
                s1.pop();
            } else {
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(everyStr)) {
                    // 将原先字符取出
                    arrayList.add(s1.pop());
                }
                // 比较完成后 将 新的 字符压入栈
                s1.push(everyStr);
            }
        }

        //todo 将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            arrayList.add(s1.pop());
        }
        return arrayList;
    }

}

//编写一个类 返回一个运算符的 对应优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }
}

