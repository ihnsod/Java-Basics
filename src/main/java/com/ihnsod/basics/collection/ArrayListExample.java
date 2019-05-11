package com.ihnsod.basics.collection;

import com.google.common.primitives.Chars;
import com.google.common.primitives.Floats;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayListExample {

    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new String("list" + i));
        }

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.err.println(iterator.next());
            iterator.remove();
        }
    }

    /**
     * 改变list集合中的某个元素的位置
     */
    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(1);
        list.add(7);
//        list.add(0,list.remove(2)); //该方法的作用是把索引为2的元素移到0索引上并把影响的索引后移
        Collections.swap(list, 0, 2);//该方法的作用是交换两个索引位置上的元素
        System.err.println(list.toString()); //[5, 1, 2, 1, 7]
    }

    @Test
    public void arrayToList() {
        char[] chars = {'a', 'b', 'c', 'd', 'e'};
        int[] ints = {1, 2, 3, 4, 5};
        long[] longs = {1, 2, 3, 4, 5};
        float[] floats = {1, 4, 4, 5, 7};
        double[] doubles = {1, 4, 4, 5, 7};

        // number类型的数据可以使用stream 中的boxed方法来获取一个对应的 具体类型的流然后在转换
        //floats除外
        List<Integer> collect = Arrays.stream(ints).boxed().collect(Collectors.toList());
        List<Long> collect1 = Arrays.stream(longs).boxed().collect(Collectors.toList());
        List<Double> collect2 = Arrays.stream(doubles).boxed().collect(Collectors.toList());

        //char[] 和 float 类型的数组可以借助google guava 工具包中提供的类型来实现
        List<Character> characters = Chars.asList(chars);
        List<Float> floats1 = Floats.asList(floats);
    }
}
