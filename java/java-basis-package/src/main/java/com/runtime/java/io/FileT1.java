package com.runtime.java.io;

import com.sun.javaws.jnl.IconDesc;
import org.junit.Test;

import java.io.*;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/13 9:27
 * @Description
 */
public class FileT1 {

    final String FILEURL = "FileT1.txt";
    final String FILEOUT = "hello.txt";

    public static void main(String[] args) {


        FileReader reader = null;
        int data;
        try {
            File file = new File("java\\java-basis-package\\FileT1.txt");
            System.out.println(file.getAbsolutePath());

            reader = new FileReader(file);

            while ((data = reader.read()) != -1) {
                System.out.print((char) data);
            }
            //C:\PxSoftwareEngineer\java\java-basis-package\src\main\java\com\runtime\java\io\FileT1.java
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // 一个一个读
    @Test
    public void test1() {
        FileReader reader = null;
        int abs;
        try {
            reader = new FileReader(new File("FileT1.txt"));
            while ((abs = reader.read()) != -1) {
                System.out.print((char) abs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 一批一批读
    @Test
    public void test2() {
        Reader reader = null;

        try {

            reader = new FileReader(new File("FileT1.txt"));

            char[] cbuf = new char[5];
            int len;

            while ((len = reader.read(cbuf)) != -1) {
                System.out.println(len);
                //for (int i = 0; i < len; i++) System.out.print(cbuf[i]);
                //for (int i =0 ;i <cbuf.length;i++) System.out.print(cbuf[i]);
                //System.out.print(new String(cbuf,0,len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 输出流
    @Test
    public void test3() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("hello.txt"));
            fileWriter.write("I live You But's in");
        } catch (Exception e) {
        } finally {
            try {
                if (fileWriter != null) fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //输入 与 输出
    @Test
    public void test4() {
        FileReader reader = null;
        FileWriter fileWriter = null;

        try {
            reader = new FileReader(FILEURL);
            fileWriter = new FileWriter(FILEOUT);

            int len;
            char[] chars = new char[5];
            while ((len = reader.read(chars)) != -1) {
                fileWriter.write(chars, 0, len);
            }
        } catch (Exception e) {
        } finally {

            try {
                if (reader != null) reader.close();
                if (fileWriter != null) fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    //*********************************** 字节流  */

    //输入 与 输出
    @Test
    public void testInput1() {
        FileInputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            //inputStream = new FileInputStream("h1.jpg");
            inputStream = new FileInputStream("D:\\Custom\\m1.mp3");
            //fileOutputStream = new FileOutputStream("h2.jpg");
            fileOutputStream = new FileOutputStream("D:\\Custom\\m2.mp3");

            byte bbyte[] = new byte[1021 * 8];
            int len;
            while ((len = inputStream.read(bbyte)) != -1) {
                fileOutputStream.write(bbyte, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //*********************************** 缓冲流  */


    @Test
    public void testBuffer1() {


        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {

            bufferedInputStream = new BufferedInputStream(new FileInputStream("D:\\Custom\\m1.mp3"));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("D:\\Custom\\m2.mp3"));

            byte[] bbyte = new byte[1024 * 8];
            int len;

            while ((len = bufferedInputStream.read(bbyte)) != -1) {
                bufferedOutputStream.write(bbyte, 0, len);
            }
        } catch (Exception e) {
        } finally {
            try {
                if (bufferedInputStream != null) bufferedInputStream.close();
                if (bufferedOutputStream != null) bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    //*********************************** 转换流 */
    @Test
    public void testTrans1() {
        //
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        try {

            inputStreamReader = new InputStreamReader(new FileInputStream("D:\\Custom\\hello1.txt"), "UTF-8");
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream("D:\\Custom\\hello2.txt"), "UTF-8");

            //byte[] bbyte = new byte[1024 * 8];
            char[] cchar = new char[1024 * 8];
            int len;

            while ((len = inputStreamReader.read(cchar)) != -1) {
                outputStreamWriter.write(cchar, 0, len);
                System.out.print(new String(cchar, 0, len));
            }
        } catch (Exception e) {
        } finally {
            try {
                if (inputStreamReader != null) inputStreamReader.close();
                if (outputStreamWriter != null) outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
