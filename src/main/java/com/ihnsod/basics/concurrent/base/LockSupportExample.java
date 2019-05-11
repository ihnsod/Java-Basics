package com.ihnsod.basics.concurrent.base;

import org.junit.Test;

import java.time.Instant;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: Ihnsod
 * @create: 2019/05/05 14:31
 **/
public class LockSupportExample {
    /**
     * ①LockSupport不需要在同步代码块里 。所以线程间也不需要维护一个共享的同步对象了，实现了线程间的解耦。
     * <p>
     * ②unpark函数可以先于park调用，所以不需要担心线程间的执行的先后顺序。
     */
    @Test
    public void util() {
        final Object obj = new Object();
        Thread A = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += i;
            }
            LockSupport.park();
            System.out.println(sum);
        });
        A.start();
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
        //Thread.sleep(1000);
        LockSupport.unpark(A);
    }

    @Test
    public void parkTime() {
        // 最多阻塞多少毫秒
        LockSupport.parkNanos(1000);
        // 参数blocker是用来标识当前线程在等待的对象（以下称为阻塞对象），该对象主要用于问题排查和系统监控。
        System.out.println("阻塞了1000纳秒");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.park(this);
        LockSupport.unpark(Thread.currentThread());
        // 阻塞当前线程 直到 deadline deadline为未来的一个时间点
        long l = Instant.now().plusSeconds(3).toEpochMilli();
        LockSupport.parkUntil(l);
        System.out.println("阻塞了3秒");

    }

    /**
     * 先park再unpark
     */
    @Test
    public void test1() {
        Object obj = new Object();
        Thread thread = new Thread(() -> {
            System.out.println("起床");
            LockSupport.park(obj);
            System.out.println("刷牙");
        });
        thread.start();
        LockSupport.unpark(thread);
        System.out.println("睁眼");
    }

    /**
     * 先interrupt再park
     */
    @Test
    public void test2() {
        Object obj = new Object();
        Thread thread = new Thread(() -> {
            System.out.println("起床");
            LockSupport.park(obj);
            System.out.println("刷牙");
            System.out.println("是否中断" + Thread.currentThread().isInterrupted());
        });
        thread.start();
//        thread.interrupt();
//        LockSupport.unpark(thread);
        System.out.println("睁眼");

    }

}
