package com.ihnsod.basics.collection;

import com.ihnsod.basics.pojo.Student;
import org.junit.Test;

import java.util.PriorityQueue;

import static java.util.Comparator.*;

/**
 * @author: Ihnsod
 * @create: 2019/01/13 00:31
 **/
public class PriorityQueueExample {

    /**
     * 优先队列
     */
    @Test
    public void noParamStructure() {
        // 优先队列为无界队列(最大为Integer.MAX_VALUE) 没有指定比较器 按照自然顺序排序
        PriorityQueue<Integer> queue = new PriorityQueue<>(5);
        // 添加元素 元素不能为null
        queue.offer(1);
        queue.offer(88);
        queue.offer(34);
        queue.offer(26);
        queue.offer(64);
        queue.offer(89);
        queue.offer(-23);
        queue.offer(-188);

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    @Test
    public void comparatorStructure() {
        // 指定比较器 先根据年龄排序 在根据名字排序
        PriorityQueue<Student> queue = new PriorityQueue<>(comparing(Student::getAge).thenComparing(Student::getName));
        // null 值判定
        PriorityQueue<Student> queueNotNullCopar = new PriorityQueue<>(comparing(Student::getAge, nullsLast(naturalOrder()))
                .thenComparing(Student::getName, nullsLast(naturalOrder())));

        queueNotNullCopar.offer(new Student().setAge(12).setName("hello"));
        queueNotNullCopar.offer(new Student().setAge(23).setName("world"));
        queueNotNullCopar.offer(new Student().setAge(34).setName("book"));
        queueNotNullCopar.offer(new Student().setAge(10).setName("hard"));
        queueNotNullCopar.offer(new Student().setAge(67).setName("wonderful"));
        queueNotNullCopar.offer(new Student().setAge(19).setName("product"));
        queueNotNullCopar.offer(new Student().setAge(null).setName("product"));
        queueNotNullCopar.offer(new Student().setAge(12).setName(null));
        queueNotNullCopar.offer(new Student().setAge(null).setName(null));

        while (!queueNotNullCopar.isEmpty()) {
            System.out.println(queue.poll());
        }

    }
}

