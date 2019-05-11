package com.ihnsod.basics.collection;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author: Ihnsod
 * @create: 2019/05/02 14:17
 **/
public class ConcurrentLinkedQueueExample {

    /**
     * ConcurrentLinkedQueue是一个基于链接节点的无边界的线程安全队列，
     * 它采用FIFO原则对元素进行排序。采用“wait-free”算法（即CAS算法）来实现的。
     * 同 LinkedBlockingQueue一样都是线程安全的 只不过阻塞队列用的是锁 而此并发队列
     * 用的是cas无锁算法 方法都差不多只不过内部原理不一样...源码还是不看了~~~
     */

    @Test
    public void structure() {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

        queue.offer("hello");
        queue.offer("world");
        queue.offer("I'm");
        queue.offer("Ihnsod");

        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }

    }

}
