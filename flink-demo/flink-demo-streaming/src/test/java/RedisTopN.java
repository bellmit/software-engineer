import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/1/14 9:25
 * @Description
 */
public class RedisTopN {
    public static void main(String[] args) {

        /***
         *  1  2  3
         *  |||  |||  |||
         *  11 22 33
         */

        HashMap<Integer, List<?>> map = new HashMap<>();

        //System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        map.put(1, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        map.put(3, Arrays.asList(11, 22, 33, 44, 55, 66, 77, 88, 99, 100));
        map.put(2, Arrays.asList(111, 222, 333, 444, 555, 666, 777, 888, 999, 1000));


        //map.get(1).getClass().newInstance().

        System.out.println(map);

    }
}
