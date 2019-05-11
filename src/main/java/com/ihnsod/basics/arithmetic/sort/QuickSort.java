package com.ihnsod.basics.arithmetic.sort;

import java.util.Arrays;

/**
 * @author: Ihnsod
 * @create: 2019/01/12 10:30
 **/
public class QuickSort {
    /**
     * 一趟快速排序的算法是：
     * 1）设置两个变量i、j，排序开始的时候：i=0，j=N-1；
     * 2）以第一个数组元素作为关键数据，赋值给key，即key=A[0]；
     * 3）从j开始向前搜索，即由后开始向前搜索(j--)，找到第一个小于key的值A[j]，将A[j]和A[i]互换；
     * 4）从i开始向后搜索，即由前开始向后搜索(i++)，找到第一个大于key的A[i]，将A[i]和A[j]互换；
     * 5）重复第3、4步，直到i=j； (3,4步中，没找到符合条件的值，即3中A[j]不小于key,4中A[i]不大于key
     * 的时候改变j、i的值，使得j=j-1，i=i+1，直至找到为止。找到符合条件的值，进行交换的时候i，
     * j指针位置不变。另外，i==j这一过程一定正好是i+或j-完成的时候，此时令循环结束）
     */
    public static void main(String[] args) {
        int[] arr = SortUtils.getArray();
        quickSort(arr, 0, arr.length - 1);
        System.err.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int low, int high) {
        //递归中可能出现 low >= high 的情况 如果不加此条件 会发声栈溢出
        if (low >= high) {
            return;
        }
        int i = low, j = high;
        //设置较小的元素为标记点
        int temp = arr[i];
        while (i < j) {
            while (i < j && arr[j] > temp) { //从j开始向前搜索，找到第一个小于key的值A[j]
                j--;
            }
            while (i < j && arr[i] <= temp) { //从i开始向后搜索，找到第一个大于key的A[i]
                i++;
            }
            if (i < j) { //如果 i<j 把 arr[i] 和 arr[j] 互换
                SortUtils.swap(arr, i, j);
            }
        }
        //改变当前标记点 此时 i 和 j 已经重合
        SortUtils.swap(arr, i, low);
        // 排序标记点左侧的数据
        quickSort(arr, low, i - 1);
        // 排序标记点右侧的数据
        quickSort(arr, i + 1, high);
    }
}
