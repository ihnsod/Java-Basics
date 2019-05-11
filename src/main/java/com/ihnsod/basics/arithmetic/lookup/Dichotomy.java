package com.ihnsod.basics.arithmetic.lookup;

import java.util.Arrays;

/**
 * @author: Ihnsod
 * @create: 2019/01/12 10:22
 **/
public class Dichotomy {
    //二分法
    public static void main(String[] args) {
        int[] arr = {1, 32, 46, 2, 4, 7, 8, 98, 65, 3, 22, 13, 24, 36, 54, 12};
        Arrays.sort(arr);
        int key = 54;
        System.err.println(dichotomy(arr, key));
    }

    //返回对应元素的对应下标
    public static int dichotomy(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < key) {
                left = mid + 1;
            }
            if (arr[mid] > key) {
                right = mid - 1;
            }
            if (arr[mid] == key) {
                return mid;
            }
        }
        return -1;
    }
}
