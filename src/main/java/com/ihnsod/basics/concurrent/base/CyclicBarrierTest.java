package com.ihnsod.basics.concurrent.base;

import java.util.concurrent.CyclicBarrier;

/**
 * @author: Ihnsod
 * @date: 2018/6/17
 */

/**
 * CyclicBarrier回环栅栏
 * 通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
 * 我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
 * 如果使用有线程的构造方法，则在所有线程达到状态之后会选择一个线程进行执行
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        int n = 4;
        CyclicBarrier barrier = new CyclicBarrier(n, new Thread(() -> {
            System.err.println("当前线程" + Thread.currentThread().getName());
        }));
        for (int i = 0; i < n; i++) {
            new Writer(barrier).start();
        }
        //CyclicBarrier可以重用

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < n; i++) {
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread {

        private CyclicBarrier barrier;

        public Writer(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.err.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(5000);
                System.err.println("线程" + Thread.currentThread().getName() + "写入数据完毕,等待其他线程写完数据。");
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.err.println("所有线程写入完毕，继续处理其他任务。");
        }
    }
}
