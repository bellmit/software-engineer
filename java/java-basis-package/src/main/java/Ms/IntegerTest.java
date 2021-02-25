package Ms;

import javax.sound.midi.Soundbank;
import java.beans.IntrospectionException;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/2/24 15:44
 * @Description
 */
public class IntegerTest {

    /**
     *  // == 比较的是地址值 equals 比较的是值 ? so 你在开玩笑吗 ?
     *
     * • 两个 int 相比 ==
     * • 两个 new Integer 相比  [== & equals]
     * • 两个 非 new Integer 相比    [== & equals]
     * • 一个 new Integer 跟 一个 int 相比 [== & equals]
     * • 一个 new Integer 跟 一个 非 new Integer 相比   [== & equals]
     * • 一个 int 跟一 非 new Integer 相比 [== & equals]
     */

    public static void main(String[] args) {

        //  两个 int 相比 ==
        int a = 1;
        int b = 1;
        System.out.println(a == b); // true 比较的是具体的值

        // 两个 new Integer 相比  [== & equals]
        Integer a1 = new Integer(100);
        Integer a2 = new Integer(100);
        System.out.println(a1 == a2); // false 比较的是 地址值
        System.out.println(a1.equals(a2)); // true 比较的是 具体的值   value == ((Integer)obj).intValue(); intValue () 返回的是 int

        //两个 非 new Integer 相比    [== & equals]
        Integer z1 = 100;
        Integer z2 = 100;
        System.out.println(z1 == z2); // true 直接进行包装 包装的方法为 valueOf(100) valueOf 涉及到缓存池
        System.out.println(z1.equals(z2)); // true


        // 一个 new Integer 跟 一个 int 相比 [== & equals]
        Integer q1 = new Integer(100);
        int q2 = 100;
        System.out.println(q1 == q2); // true 拆包 比较的是 具体的值  intValue () 返回的是 int
        System.out.println(q1.equals(q2)); // true

        // 一个 new Integer 跟 一个 非 new Integer 相比   [== & equals]
        Integer w1 = new Integer(100);
        Integer w2 = 100;
        System.out.println(w1 == w2); // false 地址值 一个在常量池 一个在 堆空间
        System.out.println(w1.equals(w2));//true 比较的是具体的值 intValue()

        // 一个 int 跟一 非 new Integer 相比 [== & equals]
        int e1 = 100;
        Integer e2 = new Integer(100);
        System.out.println(e1 == e2); // true
        System.out.println(e2.equals(e1)); // true


        // Integer 缓存以外的数字 -128 ~ 127

        Integer c1 = new Integer(129);
        Integer c2 = 129;
        System.out.println(c1.equals(c2)); // true 具体值相同
        System.out.println(c1 == c2); // false 引用不同




    }
}
