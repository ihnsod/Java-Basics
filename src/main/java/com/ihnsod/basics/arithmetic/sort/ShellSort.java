package com.ihnsod.basics.arithmetic.sort;

import java.util.Arrays;

/**
 * @author: Ihnsod
 * @create: 2019/01/12 17:32
 **/
public class ShellSort {
    //希尔排序（Shell Sort）

    /**
     * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = SortUtils.getArray();
        shellSort(array);
        System.err.println(Arrays.toString(array));
    }

    private static void shellSort(int[] array) {
        if (SortUtils.checkArray(array)) {
            int len = array.length;
            int temp, gap = len / 2;
            while (gap > 0) {
                for (int i = gap; i < len; i++) {
                    temp = array[i];
                    int preIndex = i - gap;
                    while (preIndex >= 0 && array[preIndex] > temp) {
                        array[preIndex + gap] = array[preIndex];
                        preIndex -= gap;
                    }
                    array[preIndex + gap] = temp;
                }
                gap /= 2;
            }
        }
    }
}
