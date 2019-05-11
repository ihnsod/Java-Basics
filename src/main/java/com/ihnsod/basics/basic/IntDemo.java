package com.ihnsod.basics.basic;

import org.junit.Test;

/**
 * @author: Ihnsod
 * @create: 2018/12/30 17:24
 **/
public class IntDemo {
    @Test
    public void method() {

        System.err.println(-14 % 3);//-2

        System.err.println(-14 % -3);//-2

        System.err.println(14 % -3);//2

        System.err.println(-5 % 2);//-1
    }

    @Test
    public void test1(){
        Integer j = 23;
        System.err.println(Integer.reverse(j));
    }
}
