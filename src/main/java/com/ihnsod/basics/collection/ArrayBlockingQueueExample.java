package com.ihnsod.basics.collection;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: Ihnsod
 * @create: 2019/05/01 17:31
 **/
public class ArrayBlockingQueueExample {

    /**
     * 基于数组的阻塞队列
     */
    @Test
    public void structure() {
        // 有界队列 构造方法必须传入队列的大小 参二标志是否使用公平锁
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(2, true);
        try {
            System.out.println(queue.size());

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
