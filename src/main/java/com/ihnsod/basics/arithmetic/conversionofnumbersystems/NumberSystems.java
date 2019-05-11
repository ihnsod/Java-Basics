package com.ihnsod.basics.arithmetic.conversionofnumbersystems;

import org.junit.Test;

/**
 * @author: Ihnsod
 * @date: 2018/5/20
 */
public class NumberSystems {

    @Test
    public void testJdk() {
        //jdk自己实现了十进制向二进制八进制十六进制的转换
        int a = 2018;
        String s = Integer.toBinaryString(a);
        String s1 = Integer.toOctalString(a);
        String s2 = Integer.toHexString(a);
        System.err.println("二进制:" + s + ",八进制:" + s1 + ",十六进制:" + s2);

    }

    public static void main(String[] args) {
        int a = 5,b = 9;
        swap(a,b);
        System.err.println(a+":"+b);
    }

    static void swap(int x,int y){
        int temp = x;
        x = y;
        y = temp;
    }

}
