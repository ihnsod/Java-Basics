package com.ihnsod.basics.concurrent.example.producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Ihnsod
 * @create: 2019/01/01 23:00
 **/
public class ProducerConsumerDemo2<T> {
    //使用 ReentrantLock 以及Condition 来实现
    //泛型任务
    private final LinkedList<T> lists = new LinkedList<T>();
    //最大的任务数
    private final int MAX_VALUE = 10;

    private int count = 0;

    private Lock lock = new ReentrantLock();

    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();


    public void put(T t) {
        try {
            lock.lock();
            while (lists.size() == MAX_VALUE) {
                //阻塞生产者
                producer.await();
            }
            ++count;
            lists.add(t);
            //通知消费者进行消费
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (lists.size() == 0) {
                consumer.await();
            }
            count--;
            t = lists.removeFirst();
            //通知生产者线程进行生产
            producer.signalAll();
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        ProducerConsumerDemo2<String> demo2 = new ProducerConsumerDemo2();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.err.println(demo2.get());
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
                    demo2.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }
    }

}
