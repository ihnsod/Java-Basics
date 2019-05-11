package com.ihnsod.basics.map;

import com.ihnsod.basics.pojo.Student;
import org.junit.Test;

import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.*;

/**
 * @author: Ihnsod
 * @create: 2019/05/03 16:05
 **/
public class ConcurrentSkipListMapExample {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    /**
     * 基于跳表实现的map结构,key和value都不允许为null
     * 同treemap一样,在构造时可以传入一个比较器对于key进行排序,不能对于value排序
     */
    @Test
    public void noArgs() {
        ConcurrentSkipListMap<Integer, String> skipListMap = new ConcurrentSkipListMap<>();

        IntStream.range(0, 10).forEach(value -> {
            skipListMap.put(RANDOM.nextInt(200), "127.0.0." + value);
        });

        SortedMap<Integer, String> sortedMap = skipListMap.tailMap(78);
        if (!sortedMap.isEmpty()) {
            System.err.println(sortedMap.get(sortedMap.firstKey()));
        } else {
            System.err.println(skipListMap.firstEntry().getValue());
        }
        System.err.println(skipListMap);

    }

    @Test
    public void allArgs() {
        ConcurrentSkipListMap<Student, Integer> skipListMap = new ConcurrentSkipListMap<>
                (comparing(Student::getAge, nullsLast(naturalOrder())).thenComparing(Student::getName, nullsLast(naturalOrder())));

        skipListMap.put(new Student().setAge(11).setName("hello"), 1);
        skipListMap.put(new Student().setAge(16).setName("a"), 6);
        skipListMap.put(new Student().setAge(9).setName("bb"), 56);
        skipListMap.put(new Student().setAge(9).setName("bc"), 32);
        skipListMap.put(new Student().setAge(1).setName("cca"),17);
        skipListMap.put(new Student().setAge(null).setName(null), 2);

        ConcurrentMap<Student, Integer> collect = skipListMap.entrySet().stream().sorted(nullsLast(Map.Entry.comparingByValue())).collect(
                Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(skipListMap);
        System.out.println(collect);

    }
}
