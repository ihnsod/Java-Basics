package com.ihnsod.basics.collection;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: Ihnsod
 * @create: 2019/05/01 20:45
 **/
public class LinkedBlockingQueueExample {

    /**
     * 基于链表的阻塞队列
     */
    @Test
    public void structure() {
        // 使用链表实现的阻塞队列,构造方法可以传入队列大小,不传入默认为Integer.MAX_VALUE
        BlockingQueue<String> queueNoP = new LinkedBlockingQueue<>();
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(2);

        /**
         *  LinkedBlockingQueue和ArrayBlockingQueue的方法基本没有区别
         *  在内部实现上 linkendLinkedBlockingQueue将读和写操作分离，可以让读写操作在不干扰对方的情况下，完成各自的功能，提高并发吞吐量。
         *  防止内存泄漏f方面 ：每次移除节点，都将节点的内容字段设置为null，迭代器也是如此，确保不会发生内存泄漏。
         *
         *  注意 : ArrayBlockingQueue和BlockingQueue添加元素动不能为null
         */

        try {
            queue.put("hello");
            queue.put("world");
            // 增加了两个元素之后队列已满,如果再进行添加,此线程就会阻塞住 直到队列中的元素被消费

            // tack方法会把元素出队出来 如果队列为空 则会阻塞线程直到队列中有元素自己本唤醒
            String take = queue.take();
            System.out.println(take);
            System.out.println(queue.size());
            // peek 方法只是查看对应位置的元素并不会出队
            String peek = queue.peek();
            System.out.println(peek);
            System.out.println(queue.size());

            // offer方法 添加元素 如果此时队列已满 则返回false否则进行添加元素 具有快速失败的性质
            queue.offer("I'm");

            // 在指定的时间内如果添加不上元素就失败
            queue.offer("ihnsod", 1, TimeUnit.SECONDS);

            // 与take不同 如果此时队列没有元素则返回null
            String poll = queue.poll();
            System.out.println(poll);

            // 也提供了在指定时间内获取不到元素时返回null
            String poll1 = queue.poll(1, TimeUnit.SECONDS);
            System.out.println(poll1);
            // add方法添加元素 此内部内部使用的offer方法来进行添加,如果offer方法返回false则抛出 queue is full异常
            // 不建议使用
            queue.add("a");
            queue.add("b");
            queue.add("c");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
