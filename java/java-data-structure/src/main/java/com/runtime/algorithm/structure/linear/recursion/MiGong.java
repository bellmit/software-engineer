package com.runtime.algorithm.structure.linear.recursion;

import java.util.Arrays;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/6/24 21:28
 * @Description
 */
public class MiGong {
    public static void main(String[] args) {

        getArr();

    }

    //todo 定义初始化 数组
    public static void getArr() {
        int[][] arr = new int[8][7];

        // 使用1 表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            arr[0][i] = 1;
            arr[7][i] = 1;
        }
        // 左右全部置为1
        for (int i = 0; i < 8; i++) {
            arr[i][0] = 1;
            arr[i][6] = 1;
        }
        //设置挡板, 1 表示
        arr[3][1] = 1;
        arr[3][2] = 1;
//		arr[1][2] = 1;
//		arr[2][2] = 1;

        // 输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        setWay(arr, 1, 1);
        System.out.println("小球走过，并标识过的 地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }


    //todo 使用递归回溯给小球找路
    //1. arr 表示地图
    //2. i,j 表示从地图的哪个位置开始出发 (1,1)
    //3. 如果小球能到 map[6][5] 位置，则说明通路找到.
    //4. 约定: 当arr[i][j] 为 0 表示该点没有走过 当为 1 表示墙  ； 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
    //5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
    public static boolean setWay(int[][] arr, int i, int j) {
        if (arr[6][5] == 2) {
            return true;
        } else {
            if (arr[i][j] == 0) { //如果当前这个点还没有走过
                arr[i][j] = 2;  // 左上右下 　－＞　下　－＞　上　－＞ 左
//                if (setWay(arr, i, j -1)) {
//                    return true;
//                } else if (setWay(arr, i - 1, j)) {
//                    return true;
//                } else if (setWay(arr, i, j + 1)) {
//                    return true;
//                } else if (setWay(arr, i + 1, j)) {
//                    return true;

                if(setWay(arr, i-1, j)) {//向上走
                    return true;
                } else if (setWay(arr, i, j+1)) { //向右走
                    return true;
                } else if (setWay(arr, i+1, j)) { //向下
                    return true;
                } else if (setWay(arr, i, j-1)){ // 向左走
                    return true;
                } else {
                    // 表示此路不通
                    arr[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

}
