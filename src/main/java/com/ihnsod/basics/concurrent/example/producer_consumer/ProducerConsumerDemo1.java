package com.ihnsod.basics.concurrent.example.producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author: Ihnsod
 * @create: 2019/01/01 23:00
 **/
public class ProducerConsumerDemo1<T> {
    //使用 wait 和 notifyAll 来实现生产者消费者模型

    //泛型任务
    private final LinkedList<T> lists = new LinkedList<T>();
    //最大的任务数
    private final int MAX_VALUE = 10;

    private int count = 0;

    public synchronized void put(T t) {
        while (lists.size() == MAX_VALUE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ++count;
        lists.add(t);
        //通知其他的线程进行执行
        this.notifyAll();
    }

    public synchronized T get() {
        T t = null;
        while (lists.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        t = lists.removeFirst();
        //通知其他线程执行
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        ProducerConsumerDemo1<String> demo1 = new ProducerConsumerDemo1();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.err.println(demo1.get());
                }
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    demo1.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }
    }

}
