package Ms;

import java.util.*;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/3/13 13:42
 * @Description
 */
public class EqualsSymbol {
    public static void main(String[] args) {


        // ^ & | ~

        // | 高位相比 有1为1否则为 0x`
        // ^ 高位相比 相同为 0 不同为 1
        // & 高位相比 两个数都为 1 则为1 否则 为 0
        // ~ 高位相比 有 0 为 1 有 1 为 0


        /**
         * 10 -> 0000 1010
         * 1  -> 0000 0001
         *    -> 0000 1011
         * 2  -> 0000 0010
         * ^  -> 0000 1000  => 8
         *
         * 10 -> 0000 1010
         * 1  -> 0000 0001
         * & ->  0000 0000
         * | ->  0000 1011
         */

        int c = 10;
        c ^= 2;
        System.out.println(c);

        int aa = 10;
        aa &= 1;
        System.out.println(aa);

        int bb = 10;
        bb |= 1;
        System.out.println(bb);


        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(1);
        }

        System.out.println(list.size());
        for (int i = 0, length = list.size(); i < length; i++) {
            list.get(i);
        }

        System.out.println(list.size());

        for (int i = 0, length = list.size(); i < length; i++) {
        }

        StringBuilder stringBuilder = new StringBuilder();


        final int MAXIMUM_CAPACITY = 1 << 30;
        System.out.println(MAXIMUM_CAPACITY);


        int newCap, newThr, oldThr = 10;

        if (((newCap = oldThr << 1) < MAXIMUM_CAPACITY &&
                16 >= 1 << 4)) {
            newThr = oldThr << 1; // double threshold
            System.out.println(newThr);

        }


        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        int[] arr = {1, 2, 3, 4, 5};


        new ArrayList<>().add(list);
        System.out.println(6 >> 1);
        TreeMap treeMap = new TreeMap<>();
   

    }


}
