package com.ihnsod.basics.concurrent.threadpool.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: Ihnsod
 * @create: 2018/11/25 16:35
 **/
public class WorkStealingPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        //jdk 1.8 新增的线程池  底层时使用的fork join 框架 进行实现
        //核心线程数是使用的cpu的 2倍 的线程数量
        ExecutorService executor = Executors.newWorkStealingPool();
        for (int i = 0; i < 10; i++) {
            executor.execute(()->
                System.err.println(Thread.currentThread().getName()+"我是jdk1.8才有的线程池"));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            sb.append(i).append(",");
        }
        executor.awaitTermination(10, TimeUnit.SECONDS);

        System.err.println(Runtime.getRuntime().availableProcessors());

        executor.shutdown();
    }
}
