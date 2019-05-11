package com.ihnsod.basics.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashSet;

/**
 * @author: Ihnsod
 * @create: 2019/05/01 16:27
 **/
public class LinkedHashSetExample {


    private LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

    @Test
    public void structure() {
        // 默认构造方法   super(16, .75f, true);
        LinkedHashSet<String> hashSet = new LinkedHashSet<>();

        //指定初始化值得构造 底层使用linkedHashMap 进行构造 super(initialCapacity, .75f, true);
        // 由于底层使用的是HashMap 线程不安全~~
        LinkedHashSet<String> hashSet1 = new LinkedHashSet<>(6);
    }

    @Test
    public void use() {
        //  输出 [h, e, l, o, w, r, d] 既保证了顺序有不允许重复 链表保证顺序 map的保证不重复
        //  如果存放的值是对象 建议重写hashcode和equals 方法
        System.out.println(linkedHashSet);
    }

    @Test
    public void stream() {
        linkedHashSet.stream().forEach(System.out::println);
    }

    @Before
    public void init() {
        // 由于linkedHashSet底层使用的是map 所以扩容和HashMap一样
        linkedHashSet.add("h");
        linkedHashSet.add("e");
        linkedHashSet.add("l");
        linkedHashSet.add("l");
        linkedHashSet.add("o");
        linkedHashSet.add("w");
        linkedHashSet.add("o");
        linkedHashSet.add("r");
        linkedHashSet.add("l");
        linkedHashSet.add("d");
    }

}
