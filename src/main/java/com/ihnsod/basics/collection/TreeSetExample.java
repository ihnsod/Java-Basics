package com.ihnsod.basics.collection;

import com.ihnsod.basics.pojo.Student;
import org.junit.Test;

import java.util.TreeSet;

import static java.util.Comparator.*;

/**
 * @author: Ihnsod
 * @create: 2019/05/02 23:05
 **/
public class TreeSetExample {
    /**
     * TreeSet 内部使用 TreeMap实现 同TreeMap和优先队列一样,构造方法可以传入一个比较器
     * 否则元素按照自然顺序排列
     */
    @Test
    public void structure() {
        // 未指定构造比较器有NPE异常隐患
        TreeSet<String> set = new TreeSet<>();
        // 构造方法指定比较器对null值进行处理,否则会有NPE异常
        TreeSet<String> compare = new TreeSet<>(nullsLast(naturalOrder()));
        set.add("hello");
        set.add("world");
        set.add("a");
        set.add("b");
        set.add("c");
        compare.add(null);  // 构造方法没指定排序规则,添加null元素排序时出现NPE
        System.out.println(set);
    }

    @Test
    public void structureCompare() {
        // 指定比较器构造 防止对象为null以及对应对象中的某个值为null
        TreeSet<Student> set = new TreeSet<>(nullsLast(comparing(Student::getAge, nullsLast(naturalOrder()))));

        set.add(new Student().setAge(11).setName("hello"));
        Student world = new Student().setAge(12).setName("world");
        set.add(world);
        set.add(new Student().setAge(9).setName("a"));
        set.add(new Student().setAge(23).setName("h"));
        set.add(new Student().setAge(null).setName("h"));
        set.add(null);
        System.out.println(set);

        // tailSet方法可以返回指定值后的所有元素(已经排好序)
        set.tailSet(world).forEach(System.out::println);
        // 获取指定元素后的最后一个元素
        set.tailSet(world).first();
        // 返回指定元素后的第一个元素
        set.tailSet(world).last();
    }

}
