package com.ihnsod.basics.concurrent.threadpool.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Ihnsod
 * @create: 2018/11/25 15:59
 **/
public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        //单线程的线程池 里面只有一个线程
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> System.err.println(Thread.currentThread()
                    .getName()+"我是一个单线程的线程池啊"));
        }
        executor.shutdown();
    }
}
