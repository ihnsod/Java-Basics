package com.ihnsod.basics.arithmetic.sort;

import java.util.Arrays;

/**
 * @author: Ihnsod
 * @create: 2019/01/12 16:21
 **/
public class InsertionSort {
    //插入排序

    /**
     * 从第一个元素开始，该元素可以认为已经被排序；
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 将新元素插入到该位置后；
     * 重复步骤2~5。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = SortUtils.getArray();
        insertSort(arr);
        System.err.println(Arrays.toString(arr));
    }

    private static void insertSort(int[] arr) {
        if (SortUtils.checkArray(arr)) {
            int current;
            for (int i = 0; i < arr.length - 1; i++) {
                //每次选取一个当前值 刚开始时默认第一个元素是已经排好序的
                current = arr[i + 1];
                int preIndex = i;
                //在已经排好序的数组中找寻要插入的位置
                while (preIndex >= 0 && current < arr[preIndex]) {
                    arr[preIndex + 1] = arr[preIndex];
                    preIndex--;
                }
                //插入
                arr[preIndex + 1] = current;
            }
        }
    }
}
