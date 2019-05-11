package com.ihnsod.basics.concurrent.threadpool.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Ihnsod
 * @create: 2018/11/25 16:15
 **/
public class FixedThreadPoolDemo {

    private static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        //创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
        // 线程池的大小一旦达到最大值就会保持不变，
        // 如果某个线程因为执行异常而结束，那么线程池会补充一个新线程

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10000; i++) {

            executor.execute(() -> integer.incrementAndGet());
        }

        executor.shutdown();
        // 等待所有线程完成任务，完成后才继续执行下一步
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        System.err.println(integer.get());

    }

}
