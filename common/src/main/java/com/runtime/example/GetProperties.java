package com.runtime.example;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/3/5 9:16
 * @Description
 */
public class GetProperties {
    static Properties prop = null;
    static {
        try {
            prop = new Properties();
            prop.load(GetProperties.class.getClassLoader().getResourceAsStream("log4j.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println(prop.getProperty("log4j.appender.stdout.layout.ConversionPattern"));
    }

}
