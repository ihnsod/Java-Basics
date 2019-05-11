package com.ihnsod.basics.concurrent.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Ihnsod
 * @create: 2018/12/04 10:44
 **/
public class WaitNotifyExample {

    /**
     * 调用 wait() 使得线程等待某个条件满足，线程在等待时会被挂起，
     * 当其他线程的运行使得这个条件满足时，其它线程会调用 notify() 或者 notifyAll() 来唤醒挂起的线程。
     *
     * 它们都属于 Object 的一部分，而不属于 Thread。
     *
     * 只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException。
     *
     * 使用 wait() 挂起期间，线程会释放锁。这是因为，如果没有释放锁，
     * 那么其它线程就无法进入对象的同步方法或者同步控制块中，那么就无法执行 notify()
     * 或者 notifyAll() 来唤醒挂起的线程，造成死锁。
     *
     */

    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            wait(); //调用wait之后此线程便会阻塞住，直到有其他的线程来唤醒此线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        WaitNotifyExample example = new WaitNotifyExample();
        executorService.execute(() -> example.after());
        executorService.execute(() -> example.before());
    }
}
