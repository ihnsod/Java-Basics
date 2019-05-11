package com.ihnsod.basics.collection;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: Ihnsod
 * @create: 2019/05/03 15:49
 **/
public class CopyOnWriteArraySetExample {

    /**
     * 基于 CopyOnWriteArrayList实现的并发set,线程安全
     */
    @Test
    public void structure() {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        set.add("hello");
        set.add("world");
        set.add(null);
        set.add(null);
        System.out.println(set);
    }
}
