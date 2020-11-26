package com.runtime.window;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/11/26 17:18
 * @Description
 */
public class abs {
    public static long abs(long a) {
        return (a < 0) ? -a : a;
    }

    public static void main(String[] args) {
        System.out.println(abs(1));
    }
}
