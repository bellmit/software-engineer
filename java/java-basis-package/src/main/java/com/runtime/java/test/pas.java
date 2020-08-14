package com.runtime.java.test;

import java.util.Arrays;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/7 20:03
 * @Description
 */
public class pas {
    public static void main(String[] args) {

        int[] arr = {12, 3, 3, 34, 56, 77, 432};

        /*for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i] / arr[0];
        }*/

       /* int temp = arr[0];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] / temp;
        }*/


        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = arr[i] / arr[0];
        }

        // String

        System.out.println(Arrays.toString(arr));
    }
}
