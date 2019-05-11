package com.ihnsod.basics.concurrent.base;

/**
 * @author: Ihnsod
 * @date: 2018/6/17
 */

import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * Semaphore翻译成字面意思为 信号量，Semaphore可以控同时访问的线程个数，
 * 通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
 * Semaphore信号量不要求线程必须要先acquire()获取一个许可才能进行release()
 */
public class SemaphoreTest1 {
    @Test
    public void test() {
        Semaphore semaphore = new Semaphore(0);
        System.err.println(semaphore.availablePermits());

        for (int i = 0; i < 10; i++) {
            semaphore.release();
            System.err.println(semaphore.availablePermits());
        }
    }

    /**
     * Semaphore产生死锁问题
     */
    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);
        new Worker1(semaphore1, semaphore2).start();
        new Worker2(semaphore1, semaphore2).start();
        //释放资源解决死锁问题
        semaphore1.release();
        semaphore2.release();
    }

    static final class Worker1 extends Thread {
        private Semaphore semaphore1;
        private Semaphore semaphore2;

        public Worker1(Semaphore semaphore1, Semaphore semaphore2) {
            this.semaphore1 = semaphore1;
            this.semaphore2 = semaphore2;
        }

        public void run() {
            try {
                semaphore1.acquire();
                System.err.println("线程" + Thread.currentThread().getName() + "semaphore1获取到资源许可");
                Thread.sleep(3000);
                semaphore2.acquire();
                System.err.println("线程" + Thread.currentThread().getName() + "semaphore2获取到资源许可");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static final class Worker2 extends Thread {
        private Semaphore semaphore1;
        private Semaphore semaphore2;

        public Worker2(Semaphore semaphore1, Semaphore semaphore2) {
            this.semaphore1 = semaphore1;
            this.semaphore2 = semaphore2;
        }

        public void run() {
            try {
                semaphore2.acquire();
                System.err.println("线程" + Thread.currentThread().getName() + "semaphore2获取到资源许可");
                Thread.sleep(3000);
                semaphore1.acquire();
                System.err.println("线程" + Thread.currentThread().getName() + "semaphore1获取到资源许可");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
