package com.ihnsod.basics.leetcode.array;

import java.util.Arrays;

/**
 * @author: Ihnsod
 * @create: 2018/08/19 00:29
 **/
public class LeetCodeArray3 {
    /**
     * 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     * <p>
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     * 说明:
     * <p>
     * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
     * 要求使用空间复杂度为 O(1) 的原地算法。
     */

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
//        int[] nums = {-1, -100, 3, 99};
//        rotate1(nums, 3);
//        rotate2(nums, 2);
        rotate3(nums, 2);
    }

    public static void rotate1(int[] nums, int k) {
        if (nums.length < 1 || k > nums.length) {
            return;
        }
        int len = nums.length;
        int[] array = new int[len];
        for (int i = k, j = 0; j < k; i--, j++) {
            array[j] = nums[len - i];
        }
        for (int i = 0, j = k; i < len - k; i++, j++) {
            array[j] = nums[i];
        }
        System.err.println(Arrays.toString(array));
    }

    public static void rotate2(int[] nums, int k) {
        if (nums.length < 1 || k > nums.length) {
            return;
        }
        int len = nums.length;
        int[] array = new int[len];
        for (int i = 0, j = k; i < len; j--, i++) {
            if (i < k) {
                array[i] = nums[len - j];
            } else {
                array[i] = nums[Math.abs(j)];
            }
        }
        System.err.println(Arrays.toString(array));
    }

    public static void rotate3(int[] nums, int k) {

    }
}
