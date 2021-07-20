package com.runtime.java.lambda;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/3/16 19:41
 * @Description
 */
public class node {

        public static void main(String[] args) {
            ArrayList<String> strings = new ArrayList<String>();
            strings.add("Hello, World!");
            strings.add("Welcome to online interview system of Acmcoder.");
            strings.add("This system is running Java 8.");

            for (String string : strings) {
                System.out.println(string);
            }

            int a, b;
            Scanner in = new Scanner(System.in);
            while(in.hasNextInt()) {
                a = in.nextInt();
                b = in.nextInt();
                System.out.printf("Your result is : %d\n", a + b);
            }
        }

}
