package Ms;

import java.util.Hashtable;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/2/26 16:48
 * @Description
 */
public class Other {
    public static void main(String[] args) {

        Hashtable<Object, Object> hashtable = new Hashtable<>();
        //new ArrayList<>()
        System.out.println(Integer.MAX_VALUE - 8);


        String i = "a";
        Integer ii = 97;
        System.out.println(i.equals(ii));

        System.out.println(i.hashCode() == ii.hashCode());

        Integer integer = new Integer(127);
        int tempi = 127;
        System.out.println(integer.equals(tempi)); // true


        // 在 Java 集合中,判断两个对象是否相等的规则是 :
        // 1. 判断两个对象的 HashCode 是否相等 (提高效率)  hashCode 相等 equals 不一定相等 a == 97
        // 2. 判断两个对象用 equals 运算是否相等 (精准比较)


    }
}
