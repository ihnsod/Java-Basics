package com.ihnsod.basics.arithmetic.sort;

import java.util.Arrays;

/**
 * @author: Ihnsod
 * @create: 2019/01/12 10:31
 **/
public class BubbleSort {
    //冒泡排序  空间复杂度 O(1) 时间复杂度 O(n^2)
    public static void main(String[] args) {
        int[] arr = SortUtils.getArray();
        bubbling(arr);
        System.err.println(Arrays.toString(arr));
    }

    public static void bubbling(int[] arr) {
        if (SortUtils.checkArray(arr)) {
            //标记位
            boolean flag = false;
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - 1 - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        SortUtils.swap(arr, j, j + 1);
                        flag = true;
                    }
                }
                if (!flag) {
                    break;
                }
            }
        }
    }
}
