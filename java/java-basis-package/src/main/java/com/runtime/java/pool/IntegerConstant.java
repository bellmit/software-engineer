package com.runtime.java.pool;

import org.junit.Test;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/1/24 22:17
 * @Description
 */
public class IntegerConstant {
    /**
     * 常量池是方法区的一部分
     */

    @Test
    public void equalsMethod() {
        final Object obj = null;
        final String str = null;
        final StringBuilder sBuilder = null;
        final StringBuffer sBuffer = null;

        //  [object equals 方法] 比地址值
        obj.equals("");

        //  [String 重写 object equals 方法]  比值 / 比长度 / 比 char
        str.equals("");

        //  [StringBuilder 没有重写]
        sBuilder.equals("");

        //  [StringBuffer 没有重写]
        sBuffer.equals("");



        /**
         * todo 结论 :
         *      - 在java中，"=="和"equals()"区别是什么？
         *      - 有问题的回答: 第一个是用来比较地址，第二个是用来比较内容
         *      - 至于比什么 还需要看自身的实现
         */

    }


}
