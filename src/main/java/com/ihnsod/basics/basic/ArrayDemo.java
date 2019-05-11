package com.ihnsod.basics.basic;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 关于数组的一些常规操作
 */
public class ArrayDemo {

    /**
     * 数组排序
     */
    @Test
    public void funk() {
        int[] arr = {1, 3, 2, 4, 5, 0};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.err.println(arr[i]);
        }
    }

    /**
     * 一维数组反转
     */
    @Test
    public void funk1() {
        Integer[] arr = {1, 3, 2, 4, 5, 0};
        //注意Arrays.asList()方法对于基本类型的数组支持度不好，不建议使用
        List<Integer> list = Arrays.asList(arr);
        Collections.reverse(list);
        System.err.println(list.toString());
    }

    /**
     * 二维数组行转列
     */
    @Test
    public void funk2() {
        int[][] oldArray = {{1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};
        //注意行转列定义新数组时行列的长度要和旧数组的行列相同
        int[][] newArray = new int[oldArray[0].length][oldArray.length];
        for (int i = 0; i < oldArray.length; i++) {
            for (int j = 0; j < oldArray[i].length; j++) {
                newArray[i][j] = oldArray[j][i];
            }
        }

        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[i].length; j++) {
                if (j == newArray[i].length - 1) {
                    System.err.println(newArray[i][j]);
                } else {
                    System.err.print(newArray[i][j] + ",");
                }
            }
        }

    }
}
