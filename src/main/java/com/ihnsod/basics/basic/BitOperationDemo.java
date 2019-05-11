package com.ihnsod.basics.basic;

import org.junit.Test;

/**
 * @author Ihnsod
 * @create 2018/06/19
 **/
public class BitOperationDemo {
    @Test
    public void test1() {

        System.err.println("左移<<<<<<<<<<<<");
        //正数左移正数位(任何数左移或者右移32的倍数都等于该数值本身)
        System.err.println(3 << 3);

        System.err.println(3 << 31);

        System.err.println(3 << 32);
        System.err.println(3 << -32);
        System.err.println(3 << 64);
        //正数左移负数位
        System.err.println(3 << -3);
        System.err.println(3 << 29);
        //负数左移正数位
        System.err.println(-3 << 3);
        //负数左移负数位
        System.err.println(-3 << -3);

        System.err.println("右移>>>>>>>>>>>>");
        //正数右移正数位
        System.err.println(3 >> 3);

        System.err.println(3 >> 31);

        System.err.println(3 >> 32);
        System.err.println(3 >> 64);
        //正数右移负数位
        System.err.println(3 >> -3);
        //负数右移正数位
        System.err.println(-6 >> 2);
        //负数右移负数位
        System.err.println(-3 >> -3);

        System.err.println("无符号右移>>>>>>>>>>>>>>>>>>>");
        //正数无符号右移正数位
        System.err.println(3 >>> 3);
        //正数无符号右移负数位
        System.err.println(3 >>> -3);
        //负数无符号右移正数位
        System.err.println(-3 >>> 3);
        //负数无符号右移负数位
        System.err.println(-3 >>> -3);
    }
}
