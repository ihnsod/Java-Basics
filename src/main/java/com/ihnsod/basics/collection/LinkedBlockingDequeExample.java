package com.ihnsod.basics.collection;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: Ihnsod
 * @create: 2019/05/02 12:29
 **/
public class LinkedBlockingDequeExample {

    /**
     * LinkedBlockingDeque则是一个由链表组成的双向阻塞队列，双向队列就意味着可以从对头、
     * 对尾两端插入和移除元素，同样意味着LinkedBlockingDeque支持FIFO、FILO两种操作方式。
     */

    @Test
    public void structure() {
        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>(2);

        // offer 方法会默认调用offerLast 方法 从队列的尾部添加元素 当队列满时返回null
        deque.offer("hello");
        // 从队列头部添加
        deque.offerFirst("hello");
        // 从队列尾部添加
        deque.offerLast("world");
        // 同样的 对于 offer take 等方法也都有first和last的相关操作

        System.out.println(deque.size());

        while (!deque.isEmpty()) {
            System.out.println(deque.pollLast());
        }
    }
}
