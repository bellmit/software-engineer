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
        t1f(); // 间接拼接时间为 t1f() : 5

        t2(); // 直接拼接时间为 t2() : 2
        t2f(); // 直接拼接时间为 t2f() : 3 --- 我认为这个最快 毕竟只创建了一个 StringBuilder 目前慢的原因可能是 --> t2() <-- 并没有直接使用 而 t2f() 需要在底层扩充字符数组 等操作 消耗了一部分时间

        tConcat(); // 1084

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

    //间接拼接  创建 N 个 StringBuilder
    public static void t1() {
        long currentTimeMillisStart = System.currentTimeMillis();

        String a = "123";
        for (int i = 0; i < Size; i++) {
            a = a + "b";
        }
        long currentTimeMillisEnd = System.currentTimeMillis();
        System.out.println("间接拼接时间为 t1() : " + (currentTimeMillisEnd - currentTimeMillisStart));
    }

    //间接拼接 创建 N个 String
    public static void tConcat() {
        long currentTimeMillisStart = System.currentTimeMillis();

        String a = "123";
        for (int i = 0; i < Size; i++) {
            a = a.concat("b");
        }
        long currentTimeMillisEnd = System.currentTimeMillis();
        System.out.println("间接拼接时间为 tConcat() : " + (currentTimeMillisEnd - currentTimeMillisStart));
    }


    // 间接拼接 创建 1 个 StringBuilder
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

    /**
     * Compiled from "StinrgTest.java"
     * public class Ms.StinrgTest {
     *   static final java.lang.Long Size;
     *
     *   public Ms.StinrgTest();
     *     Code:
     *        0: aload_0
     *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *        4: return
     *
     *   public static void main(java.lang.String[]);
     *     Code:
     *        0: invokestatic  #2                  // Method t1:()V
     *        3: invokestatic  #3                  // Method t2:()V
     *        6: invokestatic  #4                  // Method t1f:()V
     *        9: invokestatic  #5                  // Method t2f:()V
     *       12: invokestatic  #6                  // Method tConcat:()V
     *       15: return
     *
     *   public static void t1();
     *     Code:
     *        0: invokestatic  #7                  // Method java/lang/System.currentTimeMillis:()J
     *        3: lstore_0
     *        4: ldc           #8                  // String 123
     *        6: astore_2
     *        7: iconst_0
     *        8: istore_3
     *        9: iload_3
     *       10: i2l
     *       11: getstatic     #9                  // Field Size:Ljava/lang/Long;
     *       14: invokevirtual #10                 // Method java/lang/Long.longValue:()J
     *       17: lcmp
     *       18: ifge          47
     *       21: new           #11                 // class java/lang/StringBuilder
     *       24: dup
     *       25: invokespecial #12                 // Method java/lang/StringBuilder."<init>":()V
     *       28: aload_2
     *       29: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       32: ldc           #14                 // String b
     *       34: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       37: invokevirtual #15                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     *       40: astore_2
     *       41: iinc          3, 1
     *       44: goto          9
     *       47: invokestatic  #7                  // Method java/lang/System.currentTimeMillis:()J
     *       50: lstore_3
     *       51: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
     *       54: new           #11                 // class java/lang/StringBuilder
     *       57: dup
     *       58: invokespecial #12                 // Method java/lang/StringBuilder."<init>":()V
     *       61: ldc           #17                 // String 间接拼接时间为 t1() :
     *       63: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       66: lload_3
     *       67: lload_0
     *       68: lsub
     *       69: invokevirtual #18                 // Method java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
     *       72: invokevirtual #15                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     *       75: invokevirtual #19                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       78: return
     *
     *   public static void tConcat();
     *     Code:
     *        0: invokestatic  #7                  // Method java/lang/System.currentTimeMillis:()J
     *        3: lstore_0
     *        4: ldc           #8                  // String 123
     *        6: astore_2
     *        7: iconst_0
     *        8: istore_3
     *        9: iload_3
     *       10: i2l
     *       11: getstatic     #9                  // Field Size:Ljava/lang/Long;
     *       14: invokevirtual #10                 // Method java/lang/Long.longValue:()J
     *       17: lcmp
     *       18: ifge          34
     *       21: aload_2
     *       22: ldc           #14                 // String b
     *       24: invokevirtual #20                 // Method java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
     *       27: astore_2
     *       28: iinc          3, 1
     *       31: goto          9
     *       34: invokestatic  #7                  // Method java/lang/System.currentTimeMillis:()J
     *       37: lstore_3
     *       38: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
     *       41: new           #11                 // class java/lang/StringBuilder
     *       44: dup
     *       45: invokespecial #12                 // Method java/lang/StringBuilder."<init>":()V
     *       48: ldc           #21                 // String 间接拼接时间为 tConcat() :
     *       50: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       53: lload_3
     *       54: lload_0
     *       55: lsub
     *       56: invokevirtual #18                 // Method java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
     *       59: invokevirtual #15                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     *       62: invokevirtual #19                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       65: return
     *
     *   public static void t1f();
     *     Code:
     *        0: invokestatic  #7                  // Method java/lang/System.currentTimeMillis:()J
     *        3: lstore_0
     *        4: new           #11                 // class java/lang/StringBuilder
     *        7: dup
     *        8: ldc           #8                  // String 123
     *       10: invokespecial #22                 // Method java/lang/StringBuilder."<init>":(Ljava/lang/String;)V
     *       13: astore_2
     *       14: iconst_0
     *       15: istore_3
     *       16: iload_3
     *       17: i2l
     *       18: getstatic     #9                  // Field Size:Ljava/lang/Long;
     *       21: invokevirtual #10                 // Method java/lang/Long.longValue:()J
     *       24: lcmp
     *       25: ifge          46
     *       28: aload_2
     *       29: ldc           #14                 // String b
     *       31: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       34: ldc           #23                 // String c
     *       36: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       39: pop
     *       40: iinc          3, 1
     *       43: goto          16
     *       46: invokestatic  #7                  // Method java/lang/System.currentTimeMillis:()J
     *       49: lstore_3
     *       50: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
     *       53: new           #11                 // class java/lang/StringBuilder
     *       56: dup
     *       57: invokespecial #12                 // Method java/lang/StringBuilder."<init>":()V
     *       60: ldc           #24                 // String 间接拼接时间为 t1f() :
     *       62: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       65: lload_3
     *       66: lload_0
     *       67: lsub
     *       68: invokevirtual #18                 // Method java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
     *       71: invokevirtual #15                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     *       74: invokevirtual #19                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       77: return
     *
     *   public static void t2();
     *     Code:
     *        0: invokestatic  #7                  // Method java/lang/System.currentTimeMillis:()J
     *        3: lstore_0
     *        4: iconst_0
     *        5: istore_2
     *        6: iload_2
     *        7: i2l
     *        8: getstatic     #9                  // Field Size:Ljava/lang/Long;
     *       11: invokevirtual #10                 // Method java/lang/Long.longValue:()J
     *       14: lcmp
     *       15: ifge          27
     *       18: ldc           #25                 // String 123bc
     *       20: astore_3
     *       21: iinc          2, 1
     *       24: goto          6
     *       27: invokestatic  #7                  // Method java/lang/System.currentTimeMillis:()J
     *       30: lstore_2
     *       31: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
     *       34: new           #11                 // class java/lang/StringBuilder
     *       37: dup
     *       38: invokespecial #12                 // Method java/lang/StringBuilder."<init>":()V
     *       41: ldc           #26                 // String 直接拼接时间为 t2() :
     *       43: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       46: lload_2
     *       47: lload_0
     *       48: lsub
     *       49: invokevirtual #18                 // Method java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
     *       52: invokevirtual #15                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     *       55: invokevirtual #19                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       58: return
     *
     *   public static void t2f();
     *     Code:
     *        0: invokestatic  #7                  // Method java/lang/System.currentTimeMillis:()J
     *        3: lstore_0
     *        4: new           #11                 // class java/lang/StringBuilder
     *        7: dup
     *        8: invokespecial #12                 // Method java/lang/StringBuilder."<init>":()V
     *       11: astore_2
     *       12: iconst_0
     *       13: istore_3
     *       14: iload_3
     *       15: i2l
     *       16: getstatic     #9                  // Field Size:Ljava/lang/Long;
     *       19: invokevirtual #10                 // Method java/lang/Long.longValue:()J
     *       22: lcmp
     *       23: ifge          39
     *       26: aload_2
     *       27: ldc           #25                 // String 123bc
     *       29: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       32: pop
     *       33: iinc          3, 1
     *       36: goto          14
     *       39: invokestatic  #7                  // Method java/lang/System.currentTimeMillis:()J
     *       42: lstore_3
     *       43: getstatic     #16                 // Field java/lang/System.out:Ljava/io/PrintStream;
     *       46: new           #11                 // class java/lang/StringBuilder
     *       49: dup
     *       50: invokespecial #12                 // Method java/lang/StringBuilder."<init>":()V
     *       53: ldc           #27                 // String 直接拼接时间为 t2f() :
     *       55: invokevirtual #13                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     *       58: lload_3
     *       59: lload_0
     *       60: lsub
     *       61: invokevirtual #18                 // Method java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
     *       64: invokevirtual #15                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     *       67: invokevirtual #19                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       70: return
     *
     *   static {};
     *     Code:
     *        0: ldc2_w        #28                 // long 100000l
     *        3: invokestatic  #30                 // Method java/lang/Long.valueOf:(J)Ljava/lang/Long;
     *        6: putstatic     #9                  // Field Size:Ljava/lang/Long;
     *        9: return
     * }
     *
     * Process finished with exit code 0
     */

}
