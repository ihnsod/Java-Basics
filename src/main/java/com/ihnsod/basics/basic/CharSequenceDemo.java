package com.ihnsod.basics.basic;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: Ihnsod
 * @create: 2018/12/29 17:53
 **/
public class CharSequenceDemo {
    @Test
    public void test() {
        String s = "HellOO";
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] <= 'z' && chars[i] >= 'a') {
                chars[i] -= 32;
            } else if (chars[i] <= 'Z' && chars[i] >= 'A') {
                chars[i] += 32;
            }
        }
        System.err.println(Arrays.toString(chars));
    }

    @Test
    public void test1() {
        char a = '毇';
        a += 32;
        System.err.println(a);

        int i = (int) a;
        System.err.println(i);
        int ca = 111111111;
        char cac = (char) ca;
        System.err.println(cac);

    }
}
