package com.ihnsod.basics.arithmetic.chapteronepractice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Practice4 {
    @Test
    public void test() {
        //在高级for循环中使用list集合中的remove方法时如果移除的是集合的最后一个元素则会报并发修改异常
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
        System.err.println(list.size());
    }
}
