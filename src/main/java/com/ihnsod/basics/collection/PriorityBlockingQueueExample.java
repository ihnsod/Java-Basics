package com.ihnsod.basics.collection;

import com.ihnsod.basics.pojo.Student;
import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

import static java.util.Comparator.*;

/**
 * @author: Ihnsod
 * @create: 2019/05/01 23:41
 **/
public class PriorityBlockingQueueExample {
    /**
     * 优先级无界阻塞队列。
     */
    @Test
    public void structure() {
        /**
         * 同arrayBlockingQueue和linked...阻塞队列一样 也提供了比如 take put offer(long ...) 类型的阻塞方法
         * 同普通的优先队列一样 可以根据所传入的比较器进行构造
         * 与普通的优先队列相比,多了一些阻塞队列的性质
         */
        PriorityBlockingQueue<String> queueNatural = new PriorityBlockingQueue<>(2, naturalOrder());

        queueNatural.put("hello");

        PriorityBlockingQueue<Student> queue = new PriorityBlockingQueue<>(2,
                comparing(Student::getAge, nullsLast(naturalOrder())).thenComparing(Student::getName, nullsLast(naturalOrder())));

        queue.offer(new Student().setAge(12).setName("hello"));
        queue.offer(new Student().setAge(23).setName("world"));
        queue.offer(new Student().setAge(34).setName("book"));
        queue.offer(new Student().setAge(10).setName("hard"));
        queue.offer(new Student().setAge(67).setName("wonderful"));
        queue.offer(new Student().setAge(19).setName("product"));
        queue.offer(new Student().setAge(null).setName("product"));
        queue.offer(new Student().setAge(12).setName(null));
        queue.offer(new Student().setAge(null).setName(null));

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

    }

}
