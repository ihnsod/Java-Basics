package com.ihnsod.basics.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Ihnsod
 * @create: 2019/05/02 09:59
 **/
public class SynchronousQueueExample {

    /**
     * SynchronousQueue性质
     * 1. 整个 queue 没有容量, 表现为, 你每次进行put值进去时, 必须等待相应的 consumer 拿走数据后才可以再次 put 数据
     * 2. queue 对应 peek, contains, clear, isEmpty ... 等方法其实是无效的
     * 3. 整个 queue 分为 公平(TransferQueue FIFO)与非公平模式(TransferStack LIFO 默认)
     * 4. 若使用 TransferQueue, 则队列中永远会存在一个 dummy node
     */
    @Test
    public void structure() {
        // 非公平模式(TransferStack LIFO 默认)
        SynchronousQueue<String> notFair = new SynchronousQueue<>();
        // 公平模式
        SynchronousQueue<String> fair = new SynchronousQueue<>(true);

        ExecutorService service = Executors.newFixedThreadPool(2);

        service.execute(new Producer(notFair));
        service.execute(new Consumer(notFair));

        try {
            service.awaitTermination(1, TimeUnit.MINUTES );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}

@Data
@AllArgsConstructor
class Producer implements Runnable {

    private AtomicInteger integer = new AtomicInteger(0);

    private SynchronousQueue<String> queue;

    public Producer(SynchronousQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (; ; ) {
            System.out.println(this.queue.offer("生产者线程添加" + integer.incrementAndGet()));
        }
    }
}

@Data
@AllArgsConstructor
class Consumer implements Runnable {

    private SynchronousQueue<String> queue;

    @Override
    public void run() {
        for (; ; ) {
            try {
                System.out.println("消费者线程取出" + this.queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
