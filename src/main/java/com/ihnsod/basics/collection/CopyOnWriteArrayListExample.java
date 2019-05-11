package com.ihnsod.basics.collection;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

/**
 * @author: Ihnsod
 * @create: 2019/05/03 15:42
 **/
public class CopyOnWriteArrayListExample {
    /**
     * 并发list,线程安全,不会出现多线程问题,空间占用较大
     */

    @Test
    public void structure() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("hello");
        list.add("world");

        Thread t1 = new Thread(() -> IntStream.range(0, 5).forEach(value -> list.add(value + "")));
        Thread t2 = new Thread(() -> IntStream.range(0, 10).forEach(value -> list.add(value + "")));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(list);

    }
}
