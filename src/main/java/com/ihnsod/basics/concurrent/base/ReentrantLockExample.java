package com.ihnsod.basics.concurrent.base;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Ihnsod
 * @date: 2018/6/17
 */
public class ReentrantLockExample {


    /**
     * Lock接口的 线程请求锁的 几个方法：
     * lock(), 拿不到lock就不罢休，不然线程就一直block。 比较无赖的做法。
     * tryLock()，马上返回，拿到lock就返回true，不然返回false。 比较潇洒的做法。
     * 带时间限制的tryLock()，拿不到lock，就等一段时间，超时返回false。比较聪明的做法。
     * <p>
     * lockInterruptibly()就稍微难理解一些。
     * 先说说线程的打扰机制，每个线程都有一个 打扰 标志。这里分两种情况，
     * 1. 线程在sleep或wait,join， 此时如果别的进程调用此进程的 interrupt（）方法，
     * 此线程会被唤醒并被要求处理InterruptedException；(thread在做IO操作时也可能有类似行为，见java thread api)
     * 2. 此线程在运行中， 则不会收到提醒。但是 此线程的 “打扰标志”会被设置，
     * 可以通过isInterrupted()查看并 作出处理。
     * <p>
     * lockInterruptibly()和上面的第一种情况是一样的， 线程在请求lock并被阻塞时，
     * 如果被interrupt，则“此线程会被唤醒并被要求处理InterruptedException”。
     */

    @Test
    public void structure() {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();

        try {

        } finally {
            lock.unlock();
        }

        // 尝试获取锁,返回成功与否
        lock.tryLock();
        try {
            // 在指定的时间内尝试获取锁
            lock.tryLock(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * lock 方法测试,可以发现在主线程调用子线程的中断方法,子线程并不会被捕获到
     * 子线程会一直阻塞到获取到锁为止
     *
     * @throws InterruptedException
     */
    @Test
    public void lockDemo() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread.sleep(1000);
        Thread t1 = new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        }
        );
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        Thread.sleep(1000000);
    }

    /**
     * lockInterruptibly 方法测试,子线程使用lockInterruptibly方法来获取锁
     * 在主线程中调用子线程的中断方法子线程会退出获取锁的行为而去处理中断
     *
     * @throws InterruptedException
     */
    @Test
    public void lockInterruptibly() throws InterruptedException {
        final Lock lock = new ReentrantLock();
        lock.lock();
        Thread.sleep(1000);
        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted.");
            }
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();
        Thread.sleep(1000000);
    }

}
