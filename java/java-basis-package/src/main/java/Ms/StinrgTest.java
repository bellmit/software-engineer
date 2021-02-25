package Ms;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/2/24 18:56
 * @Description
 */
public class StinrgTest {
    final static Long Size = 100_000L;

    public static void main(String[] args) {
        t1(); // 间接拼接时间为 t1() : 4488
        t2(); // 直接拼接时间为 t2() : 2
        t1f(); // 间接拼接时间为 t1f() : 5
        t2f(); // 直接拼接时间为 t2f() : 3
        tConcat();

//        String a1 = "Hello";
//        String a2 = a1 + " Word" + " 123" + a1;
//
//        String a3 = "Hello" + " Word";
//        System.out.println(a2 == a3);

//        String s1 = "123";
//        String s2 = new String("123");
//        System.out.println(s1 == s2);
//
//        String s3 = "Hello ";
//        String s4 = "Hello ";
//        System.out.println(s3 == s4);
//
//        String temp = "a";
//        for (int i= 0; i < 10000 ; i++) {
//            temp += "a";
//        }
//        System.out.println(temp.length());
    }

    // 直接拼接 创建 N 个 StringBuilder
    public static void t1() {
        long currentTimeMillisStart = System.currentTimeMillis();

        String a = "123";
        for (int i = 0; i < Size; i++) {
            a = a + "b";
        }
        long currentTimeMillisEnd = System.currentTimeMillis();
        System.out.println("间接拼接时间为 t1() : " + (currentTimeMillisEnd - currentTimeMillisStart));
    }

    //
    public static void tConcat() {
        long currentTimeMillisStart = System.currentTimeMillis();

        String a = "123";
        for (int i = 0; i < Size; i++) {
            a = a.concat("b");
        }
        long currentTimeMillisEnd = System.currentTimeMillis();
        System.out.println("间接拼接时间为 tConcat() : " + (currentTimeMillisEnd - currentTimeMillisStart));
    }


    // 直接拼接 创建 1 个 StringBuilder
    public static void t1f() {
        long currentTimeMillisStart = System.currentTimeMillis();

        StringBuilder stringBuilder = new StringBuilder("123");
        for (int i = 0; i < Size; i++) {
            stringBuilder.append("b").append("c");
        }
        long currentTimeMillisEnd = System.currentTimeMillis();
        System.out.println("间接拼接时间为 t1f() : " + (currentTimeMillisEnd - currentTimeMillisStart));
    }


    // 直接拼接 创建 N 个 StringBuilder
    public static void t2() {
        long currentTimeMillisStart = System.currentTimeMillis();

        for (int i = 0; i < Size; i++) {
            String a = "123" + "b" + "c";
        }
        long currentTimeMillisEnd = System.currentTimeMillis();
        System.out.println("直接拼接时间为 t2() : " + (currentTimeMillisEnd - currentTimeMillisStart));
    }

    // 直接拼接 创建 1 个 StringBuilder
    public static void t2f() {
        long currentTimeMillisStart = System.currentTimeMillis();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < Size; i++) {
            stringBuilder.append("123" + "b" + "c");
        }
        long currentTimeMillisEnd = System.currentTimeMillis();
        System.out.println("直接拼接时间为 t2f() : " + (currentTimeMillisEnd - currentTimeMillisStart));
    }


}
