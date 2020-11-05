package com.test;

import com.runtime.date.DateUtils;

import java.util.Date;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/9/12 11:20
 * @Description
 */
public class dateTest {
    public static void main(String[] args) {

        System.out.println(DateUtils.str2Date(DateUtils.addDay(new Date(),1).toString(), DateUtils.FORMAT_YMDHMS));
        System.out.println(DateUtils.getMillon(System.currentTimeMillis()));
        System.out.println(DateUtils.getDay(System.currentTimeMillis()));
        //System.out.println();


    }
}
