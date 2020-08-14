package com.runtime.java.test.string;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/12 9:59
 * @Description
 */
public class StringTest {
    public static void main(String[] args) {

        String str1 = "ab";
        String str2 = "cd";
        final String str3 = str1 + str2;

        System.out.println("abcd" == str3.intern());
        //str1 + str2;
        //str1.concat()

        StringBuilder stringBuilder = new StringBuilder();
        ///stringBuilder


    }
}
