package com.ihnsod.basics.basic;

import org.junit.Test;

/**
 * @author: Ihnsod
 * @create: 2018/12/29 17:49
 **/
public class MathDemo {
    @Test
    public void test(){
        System.err.println(Math.round(11.1));
        System.err.println(Math.round(-11.1));

    }

    @Test
    public void test1(){
        int x = 2,y = 3;
        System.err.println(Math.pow(x,y));
        //当y的参数为一个分数时,jdk会把改分数转换为int类型的值,再进行计算 比如1/3为 0 2的0次方为0
        System.err.println(Math.pow(x,1/3));
    }
}
