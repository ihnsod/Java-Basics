package com.ihnsod.basics.concurrent.threadpool.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Ihnsod
 * @create: 2018/11/25 16:24
 **/
public class ScheduledThreadPoolDemo {
    //此线程池支持定时以及周期性执行任务的需求。 底层使用的是延迟队列
    public static void main(String[] args) {
        ExecutorService executor = Executors.newScheduledThreadPool(8);
        for (int i = 0; i < 11; i++) {
            ((ScheduledExecutorService) executor).schedule(() ->
                    System.err.println(Thread.currentThread().getName() + "我是能定期执行的线程池"),
                    10, TimeUnit.MINUTES);
        }

        executor.shutdown();
    }

}
