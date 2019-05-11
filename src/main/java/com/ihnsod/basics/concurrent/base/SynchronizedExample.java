package com.ihnsod.basics.concurrent.base;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Ihnsod
 * @create: 2018/12/04 09:43
 **/
public class SynchronizedExample {
    public void function() throws InterruptedException {
        Random random = new Random();
        //1. 同步一个代码块
        //它只作用于同一个对象，如果调用两个对象上的同步代码块，就不会进行同步。
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(random.nextInt(10));
                System.err.print(i);
            }
        }
    }

    public synchronized void function1() throws InterruptedException {
        Random random = new Random();
        //1. 同步一个方法 和同步一个代码快一样
        //它只作用于同一个对象，如果调用两个对象上的同步代码块，就不会进行同步。
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(random.nextInt(10));
                System.err.print(i);
            }
        }
    }

    public void function2() throws InterruptedException {
        Random random = new Random();
        //1. 同步一个类 作用于整个类，
        // 也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(random.nextInt(10));
                System.err.print(i);
            }
        }
    }

    public static synchronized void function3() throws InterruptedException {
        Random random = new Random();
        //1. 同步一个类静态方法也作用于整个类，
        // 也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(random.nextInt(10));
                System.err.print(i);
            }
        }
    }



    /**
     * 对于以下代码，使用 ExecutorService 执行了两个线程，由于调用的是同一个对象的同步代码块，
     * 因此这两个线程会进行同步，当一个线程进入同步语句块时，另一个线程就必须等待。
     */
    public static void main(String[] args) {
        SynchronizedExample e1 = new SynchronizedExample();
        SynchronizedExample e2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            try {
                e1.function2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            try {
//                e1.function();
                //如果调用对象2的代码就不会进行同步
                e2.function2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
