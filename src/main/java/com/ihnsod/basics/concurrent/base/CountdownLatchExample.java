package com.ihnsod.basics.concurrent.base;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: Ihnsod
 * @create: 2018/12/04 10:48
 **/
@Slf4j
public class CountdownLatchExample {
    /**
     * CountDownLatch可以用于多个任务无顺序的的执行情景,但要注意处理每个子任务的异常情况以及主线程
     * await阻塞时间过长的问题
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch cdl = new CountDownLatch(3);
        executorService.execute(() -> {
            try {
                function1();
            } catch (Exception e) {
                //异常处理
                log.error("任务一执行失败", e);
                e.printStackTrace();
            } finally {
                cdl.countDown();
            }
        });

        executorService.execute(() -> {
            function2();
            cdl.countDown();
        });

        executorService.execute(() -> {
            function3();
            cdl.countDown();
        });

        try {
            // cdl.await();
            cdl.await(2, TimeUnit.SECONDS);
            System.out.println("三个执行线程结束");
        } catch (Exception e) {
            log.error("多任务执行失败", e);
            e.printStackTrace();
            System.out.println("执行线程异常");
        } finally {
            log.warn("**任务执行失败,线程池关闭");
            executorService.shutdown();
            System.out.println("执行线程关闭");
        }

    }

    private static void function1() {
        int i = 10 / 0;
        System.out.println("方法一");
    }

    private static void function2() {
        System.out.println("方法二");
    }

    private static void function3() {
        System.out.println("方法三");
    }
}
