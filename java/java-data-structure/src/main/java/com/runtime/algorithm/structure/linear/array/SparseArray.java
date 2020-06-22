package com.runtime.algorithm.structure.linear.array;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/22 13:20
 * @Description 稀疏数组
 */
public class SparseArray {
    static private int COUNT;

    public static void main(String[] args) {

        // 创建一个原始的二维数组 11 * 11 | 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int[][] chessArr1 = new int[11][11];

        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;


        // 创建稀疏数组
        // todo : 1 明确 chessArr1 不一样的值有多少
        for (int[] everyOneArray : chessArr1) {
            for (int array : everyOneArray) {
                if (array != 0) {
                    COUNT++;
                }
            }
        }

        // todo : 2 创建稀疏数组
        int[][] sparseArr = new int[++COUNT][3];

        // todo : 3 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = COUNT;

        //todo : 4 遍历二维数组，将非0的值存放到 sparseArr 中
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }


        // todo : 5 输出稀疏数组的形式
        for (int[] ints : sparseArr) {
            System.out.println(Arrays.toString(ints));
            System.out.println();
        }


        // todo : 将稀疏数组 --》 恢复成 原始的二维数组

        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给 原始的二维数组 即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        try {
            dataOutputStream(sparseArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void dataOutputStream(int[][] arrO1) throws Exception {
        DataOutputStream stream = new DataOutputStream(new FileOutputStream(new File("D:\\a1.txt")));
        for (int[] everyOne : arrO1) {
            for (int everyTwo : everyOne) {
                stream.write(Byte.parseByte(String.valueOf(everyTwo)));
            }
        }
    }

}
