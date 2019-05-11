package com.ihnsod.basics.basic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeMap;

public class LocalDate {
    @Test
    public void test1() {
        java.time.LocalDate now = java.time.LocalDate.now();
        System.err.println(now);

        ArrayList list = new ArrayList();
        list.contains("q");
        HashSet set = new HashSet();
        set.contains("q");

        TreeMap map = new TreeMap();
        map.containsKey("q");

        list.size();
    }

    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        ArrayList list = new ArrayList(100000000);
        for (int i = 0; i < list.size(); i++) {
            System.err.println(i);
        }
        long end = System.currentTimeMillis();
        System.err.println("消耗时间" + (end - start));
    }

    @Test
    public void test3() {
        long start = System.currentTimeMillis();
        ArrayList list = new ArrayList(100000000);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.err.println(i);
        }
        long end = System.currentTimeMillis();
        System.err.println("消耗时间" + (end - start));
    }

    @Test
    public void test4() {
        String str = "12Y34h56dAd7";
        String regex = "[a-zA-Z]+";
        System.out.println(str.replaceAll(regex, "-"));
        System.err.println(str.matches(regex));
        String[] split = str.split(regex);
        System.err.println(Arrays.toString(split));
        //第二个参数是确定要切割成几份
        System.err.println(Arrays.toString(str.split(regex, 2)));

    }

    @Test
    public void test5(){

    }

}
