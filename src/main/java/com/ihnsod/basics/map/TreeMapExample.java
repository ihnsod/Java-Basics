package com.ihnsod.basics.map;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: Ihnsod
 * @create: 2019/04/28 22:51
 **/
public class TreeMapExample {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    /**
     * treeMap 排序map,构造时可以传入一个key的比较器
     */
    @Test
    public void noArgs() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        IntStream.range(0, 10).forEach(value -> {
            treeMap.put(RANDOM.nextInt(200), "127.0.0." + value);
        });
        // 获取指定key之后的元素
        SortedMap<Integer, String> sortedMap = treeMap.tailMap(78);
        if (!sortedMap.isEmpty()) {
            System.err.println(sortedMap.get(sortedMap.firstKey()));
        } else {
            System.err.println(treeMap.firstEntry().getValue());
        }
        System.err.println(treeMap);
    }

    @Test
    public void allArgs() {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        IntStream.range(0, 10).forEach(value -> treeMap.put(RANDOM.nextInt(200), RANDOM.nextInt(100)));

        System.out.println(treeMap);
        // 根据value排序
        LinkedHashMap<Integer, Integer> collect = treeMap.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));

        System.out.println(collect);
    }

    @Test
    public void sortStructure(){

    }
}
