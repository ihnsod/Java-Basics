package com.ihnsod.basics.arithmetic.sort;

/**
 * @author: Ihnsod
 * @create: 2019/01/12 16:52
 **/
public class SortUtils {

    protected static int[] getArray() {
        return new int[]{1, 32, 46, 2, 4, 7, 8, 98, 65, 3, 22, 13, 24, 36, 54, 12};
    }

    protected static void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }

    protected static boolean checkArray(int[] arr) {
        return arr != null && arr.length >= 1;
    }
}
