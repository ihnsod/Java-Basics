package com.ihnsod.basics.concurrent.threadpool.jdk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: Ihnsod
 * @create: 2018/11/25 16:20
 **/
public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        //创建一个可以缓存的线程池，如果线程池的大小超过了处理任务所需要的线程，
        //那么就会回收部分空闲的线程，当任务数增加时，此线程池又添加新线程来处理任务。
        //底层使用的是SynchronousQueue 来实现 但有任务提交时 会马上就把此任务交给在空闲时的
        //线程 如果没有则创建一个新的线程来执行此任务
        ExecutorService executor = Executors.newCachedThreadPool();

        executor.execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        /**
         * 调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，
         * 但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。
         */

        executor.shutdownNow();
        System.out.println("Main run");

        /**
         * 如果只想中断 Executor 中的一个线程，可以通过使用 submit() 方法来提交一个线程，
         * 它会返回一个 Future<?> 对象，通过调用该对象的 cancel(true) 方法就可以中断线程。
         */

        Future<?> future = executor.submit(() -> {
            //
        });

        future.cancel(true);


    }

}
