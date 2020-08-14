package com.runtime.java.io;

import javax.xml.transform.Source;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/13 16:53
 * @Description
 */
public class SystmeIn {
    public static void main(String[] args) {


        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new InputStreamReader(System.in));


            //byte[] bbyte = new byte[1024 * 8];
            char[] cchar = new char[1024 * 8];
            int len;

            while (true) {

                System.out.println("输入");
                String line = bufferedReader.readLine();

                if ("e".equalsIgnoreCase(line) || "exit".equalsIgnoreCase(line)) {
                    break;
                }
                System.out.println(line.toUpperCase());

            }

        } catch (Exception e) {
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


