package com.ihnsod.basics.arithmetic.sort;

import java.util.Arrays;

/**
 * @author: Ihnsod
 * @create: 2019/01/12 16:42
 **/
public class SelectionSort {
    //选择排序

    /**
     * 工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
     * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = SortUtils.getArray();
        selectSort(arr);
        System.err.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        if (SortUtils.checkArray(arr)) {
            for (int i = 0; i < arr.length; i++) {
                int min = i;
                //内循环从 i 开始 因为每次外循环都会找到一个最小的放在最左边
                for (int j = i; j < arr.length; j++) {
                    if (arr[j] < arr[min]) {
                        min = j;
                    }
                }
                //交换最小值的位置
                SortUtils.swap(arr, min, i);
            }
        }
    }
}
