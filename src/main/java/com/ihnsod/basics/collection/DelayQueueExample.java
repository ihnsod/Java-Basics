package com.ihnsod.basics.collection;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Test;

import java.time.Instant;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author: Ihnsod
 * @create: 2019/05/02 00:02
 **/
public class DelayQueueExample {

    /**
     * 延时队列
     * DelayQueue是一个支持延时获取元素的无界阻塞队列。里面的元素全部都是“可延期”的元素，列头的元素是最先“到期”的元素，
     * 如果队列里面没有元素到期，是不能从列头获取元素的，哪怕有元素也不行。也就是说只有在延迟期到时才能够从队列中取元素
     */
    @Test
    public void structure() {
        DelayQueue<Task> delayQueue = new DelayQueue<>();

        Instant now = Instant.now();

        delayQueue.put(new Task.TaskBuilder().name("任务一").delayTime(now.plusMillis(1000).toEpochMilli()).build());
        delayQueue.put(new Task.TaskBuilder().name("任务二").delayTime(now.plusMillis(1500).toEpochMilli()).build());
        delayQueue.put(new Task.TaskBuilder().name("任务三").delayTime(now.plusMillis(6000).toEpochMilli()).build());
        delayQueue.put(new Task.TaskBuilder().name("任务四").delayTime(now.plusMillis(3200).toEpochMilli()).build());
        delayQueue.put(new Task.TaskBuilder().name("任务五").delayTime(now.plusMillis(4000).toEpochMilli()).build());
        delayQueue.put(new Task.TaskBuilder().name("任务六").delayTime(now.plusMillis(1600).toEpochMilli()).build());
        delayQueue.put(new Task.TaskBuilder().name("任务七").delayTime(now.plusMillis(10000).toEpochMilli()).build());

        while (!delayQueue.isEmpty()) {
            try {
                Task poll = delayQueue.take();
                System.out.println(poll.getName() + "::" + poll.getDelayTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}

@Data
@Builder
@Accessors(chain = true)
class Task implements Delayed {
    // 延迟时间
    private long delayTime;
    // 任务名字
    private String name;

    @Override
    public long getDelay(TimeUnit unit) {
        Instant now = Instant.now();
        long surplus = delayTime - now.toEpochMilli();
        return unit.convert(surplus, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this == o) {
            return 0;
        }
        if (o instanceof Task) {
            Task task = (Task) o;
            long delayTime = this.getDelayTime();
            long taskDelayTime = task.getDelayTime();
            return delayTime > taskDelayTime ? 1 : delayTime < taskDelayTime ? -1 : 0;
        }
        return -1;
    }
}

class RunnableTask implements Runnable {

    @Override
    public void run() {

    }
}
