package Ms;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/2/25 17:07
 * @Description
 */
public class HashCodeAndEquals {
    public static void main(String[] args) {

        // HashCode  与 equals 的区别

        // HashCode 相等的情况下,对象值不一定相等 [比如: Integer integerTemp = 97; || String stringTemp = "a";
        // 如果 equals 相等,那么 HashCode 必须相等
        // 重写 equals 的情况下 也需要按照 上面的原则 重写 hashcode 方法

        // 在 Java 集合中,判断两个对象是否相等的规则是 :
        // 1. 判断两个对象的 HashCode 是否相等 (提高效率)  hashCode 相等 equals 不一定相等 a == 97
        // 2. 判断两个对象用 equals 运算是否相等 (精准比较)


        Integer integerTemp = 97;
        String stringTemp = "a";

        System.out.println(integerTemp.hashCode());
        System.out.println(stringTemp.hashCode());

        //OverRidingEqualsAndOverRidingHashCode(); //重写 equals  重写/不重写 hashCode 效果


        String str1 = new String("over");
        String str2 = new String("over");
        System.out.println("str1.hashCode()" + str1.hashCode()); // true
        System.out.println("str2.hashCode()" + str2.hashCode()); // true

        System.out.println(str1.equals(str2)); // true

        System.out.println("over".equals(str1)); // true
        System.out.println("over".hashCode()); // true

        System.out.println("over" == str1); // false



        HashSet<String> set = new HashSet<>(8);
        set.add(str1);
        set.add(str2);

        for (String s : set) {
            System.out.println(s);
        }


    }

    /**
     * 重写 equals  重写/不重写 hashCode 效果
     */
    public static void OverRidingEqualsAndOverRidingHashCode() {
        HashCE hashCE1 = new HashCE("field1", "field2");
        HashCE hashCE2 = new HashCE("field1", "field2");

        System.out.println(hashCE1 == hashCE2); // false 比较地址值
        System.out.println(hashCE1.equals(hashCE2)); // false 未重写 equals 比较地址值

        // 重写 equals 方法 但不重写 hashcode
        System.out.println("// 重写 equals 方法 但不重写 hashcode");
        System.out.println(hashCE1.hashCode()); // 1836019240
        System.out.println(hashCE2.hashCode()); // 325040804


        // 重写 equals 方法 并重写 hashcode
        System.out.println("// 重写 equals 方法 并重写 hashcode");
        System.out.println(hashCE1.hashCode()); // -2135960926
        System.out.println(hashCE2.hashCode()); // -2135960926
    }

    static class HashCE {
        String field1;
        String field2;

        public HashCE(String field1, String field2) {
            this.field1 = field1;
            this.field2 = field2;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true; // 地址值相等
            if (o == null || getClass() != o.getClass()) return false;

            HashCE hashCE = (HashCE) o;
            return Objects.equals(field1, hashCE.field1) && Objects.equals(field2, hashCE.field2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(field1, field2);
        }
    }


}
