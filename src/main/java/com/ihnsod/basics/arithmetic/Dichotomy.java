package com.ihnsod.basics.arithmetic;


import java.util.*;

/**
 * 使用二分查找的前提条件是线性表必须以顺序方式(即是数组这种)存储，链式存取是链表，并且按关键码值排好序
 */

public class Dichotomy {


    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        Random rd = new Random();
        for (int i = 0; i < 10000; i++) {
            list.add(rd.nextInt(1000));
            System.out.println(list.get(i));
        }

        Collections.sort(list);

        /**
         * 如果使用list.toArray()方法进行转化，结果为一个Object数组，不能再强转为其他类型,如果使用list.toArray(T [])
         * 要指定数组的类型和大小
         */
        Integer[] arr = list.toArray(new Integer[list.size()]);

        //使用java8中的流式操作使Integer数组变为Int数组
        int[] array = Arrays.stream(arr).mapToInt(Integer::intValue).toArray();

        long start = System.currentTimeMillis();
        int rank = rank(50, array);
        long end = System.currentTimeMillis();
        System.err.println("rank=" + rank);
        if (rank != -1) {
            System.err.println("找到的数字" + arr[rank]);
        }
        System.err.println("所用时间=" + (end - start));

    }

    /**
     * 使用两个方法实现递归
     *
     * @param key
     * @param arr
     * @return
     */

    public static int rank(int key, int[] arr) {
        return rank(key, arr, 0, arr.length - 1);
    }

    public static int rank(int key, int[] arr, int left, int right) {
        if (left > right) {
            System.err.println("没有找到与目标相同的数字");
            return -1;
        }
        //设置中间的索引值
        int center = left + (right - left) / 2;
        //从右半边找
        if (key > arr[center]) {
            return rank(key, arr, center + 1, right);
        } else if (key < arr[center]) { //从左半边找
            return rank(key, arr, left, center - 1);
        } else {
            return center;    //找到返回
        }

    }

    /**
     * 在一个方法中实现递归二分
     *
     * @param key
     * @param arr
     * @return
     */
    public static int rankOne(int key, int[] arr) {

        int left = 0, right = arr.length - 1;
        int center = left + (right - left) / 2;
        while (left <= right) {
            if (key > arr[center]) {
                left = center + 1;
            } else if (key < arr[center]) {
                right = center - 1;
            } else {
                return center;
            }
        }
        return -1;
    }

}
