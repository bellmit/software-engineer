package com.runtime.java.io;

import java.io.File;
import java.io.IOException;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/14 10:27
 * @Description
 */
public class FileMark {
    public static void main(String[] args) throws IOException {


        File dir1 = new File("D:\\Custom\\dir1");
       /* if (!dir1.exists()) { // 如果D:/IOTest/dir1不存在，就创建为目录
            dir1.mkdir();
        }*/


        File dir2 = new File(dir1, "dir2");
        //dir2.delete();

        if (!dir2.exists()) {
            //dir2.createNewFile();
            //dir2.mkdirs();
            //dir2.mkdir();
            //dir2.delete();
        }

    }
}

