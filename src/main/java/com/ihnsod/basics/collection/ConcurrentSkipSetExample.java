package com.ihnsod.basics.collection;

import com.ihnsod.basics.pojo.Student;
import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListSet;

import static java.util.Comparator.*;

/**
 * @author: Ihnsod
 * @create: 2019/05/03 15:09
 **/
public class ConcurrentSkipSetExample {

    /**
     * 基于跳表实现的Set,元素不允许为null(如果存入的是对象类型,对象的属性可以为null)
     * 并发安全性set集合 内部使用ConcurrentSkipListMap来实现
     */
    @Test
    public void noArgs() {
        ConcurrentSkipListSet<String> listSet = new ConcurrentSkipListSet<>();

        listSet.add("hello");
        listSet.add("world");
        listSet.add(null); // 同TreeSet一样 构造方法如果不指定排序规则,添加的元素不能为null
    }

    @Test
    public void allArgs() {
        // 同TreeSet不同,就算指定了比较器,也不允许元素为null
        ConcurrentSkipListSet<String> listSet = new ConcurrentSkipListSet<>(nullsLast(naturalOrder()));

        listSet.add("hello");
        listSet.add("world");
        listSet.add(null); // 同TreeSet一样 构造方法如果不指定排序规则,添加的元素不能为null
    }

    @Test
    public void allPojoArgs() {
        // 同TreeSet不同,就算指定了比较器,也不允许元素为null,如果是对象类型,对象内的元素允许为null
        ConcurrentSkipListSet<Student> set = new ConcurrentSkipListSet<>(nullsLast(comparing(Student::getAge, nullsLast(naturalOrder()))));
        set.add(new Student().setAge(11).setName("hello"));
        Student world = new Student().setAge(12).setName("world");
        set.add(world);
        set.add(new Student().setAge(9).setName("a"));
        set.add(new Student().setAge(23).setName("h"));
        set.add(new Student().setAge(null).setName("h"));
//        set.add(null);
        System.out.println(set);
    }


}
