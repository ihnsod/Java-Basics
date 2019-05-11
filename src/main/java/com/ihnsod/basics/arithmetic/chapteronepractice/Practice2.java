package com.ihnsod.basics.arithmetic.chapteronepractice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Practice2 {


    public static void main(String[] args) {

    }

    /**
     * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
     * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
     */
    @Test
    public void sum() {
        int[] nums = {2, 7, 11, 15, 3, 6, 1, 1, 2, 8};
        int target = 9;

        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i != nums.length - 1) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        ArrayList<Integer> list1 = new ArrayList<>();
                        list1.add(i);
                        list1.add(j);
                        list.add(list1);
                    }
                }
            }
        }
        System.err.println("数组中两个值和的加和和目标值相同的如下");
        System.err.println(list.toString());
    }

    /**
     * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    @Test
    public void sum1() {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(2);
        list1.add(4);
        list1.add(3);
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(5);
        list2.add(6);
        list2.add(4);

        int length = list1.size();

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int sum = list1.get(i) + list2.get(i);
            if (sum >= 10) {
                list.add(sum - 10);
                //随意给list1或者list2链表中的下一个值加1
                list1.set(i + 1, list1.get(i + 1) + 1);
            } else {
                list.add(sum);
            }
        }

        System.err.println(Arrays.toString(list.toArray(new Integer[list.size()])));
    }

    /**
     * 给定一个字符串，找出不含有重复字符的最长子串的长度。
     * 示例：给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
     * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
     * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
     */
    @Test
    public void sonStr() {
        String str = "javazxxhelloworld";
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (chars.contains(str.charAt(i))) {
                chars.removeAll(chars.subList(0, chars.indexOf(str.charAt(i)) + 1));
                chars.add(str.charAt(i));
            } else {
                chars.add(str.charAt(i));
            }
        }
        System.err.println(chars.toString());
    }

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
     * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
     * 示例 1:
     * nums1 = [1, 3]
     * nums2 = [2]
     * 中位数是 2.0
     * 示例 2:
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * 中位数是 (2 + 3)/2 = 2.5
     */
    @Test
    public void center1() {

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++) {


            Integer[] nums1 = {1, 2, 4, 6, 2, 12, 34, 56, 23, 4, 22, 3, 45, 56, 7, 2, 34};
            Integer[] nums2 = {2, 13, 43, 2, 3, 2, 2, 22, 35, 67, 32, 67, 11, 88, 44};

            //要new一个新的集合,Arrays.asList()后的集合不能被改变
            List<Integer> list = new ArrayList<>(Arrays.asList(nums1));
            List<Integer> list2 = new ArrayList<>(Arrays.asList(nums2));

            list.addAll(list2);
            Collections.sort(list);

            int center;
            int size = list.size();
            if (size % 2 == 0) {
                center = list.get(size / 2) + list.get(size / 2 + 1);
            } else {
                center = list.get((size + 1) / 2);
            }

            System.err.println("两数组中间值为" + center);
        }
        long end = System.currentTimeMillis();
        System.err.println("消耗时间为:" + (end - start));

    }

    @Test
    public void center2() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {


            int[] arr1 = {1, 2, 4, 6, 2, 12, 34, 56, 23, 4, 22, 3, 45, 56, 7, 2, 34};
            int[] arr2 = {2, 13, 43, 2, 3, 2, 2, 22, 35, 67, 32, 67, 11, 88, 44};

            int[] arr = new int[arr1.length + arr2.length];
            //使用系统类中的数组copy方法 参一源数组，参数二源数组开始的索引，参数三目标数组，参数四，目标数组的开始索引，参数五，要copy多长的数据到目标数组中
            System.arraycopy(arr1, 0, arr, 0, arr1.length);
            System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);

            Arrays.sort(arr);
            int center;
            int length = arr.length;
            if (length % 2 == 0) {
                center = arr[length / 2] + arr[length / 2 + 1];
            } else {
                center = arr[(length + 1) / 2];
            }

            System.err.println("两数组中间值为" + center);
        }
        long end = System.currentTimeMillis();

        System.err.println("消耗时间为:" + (end - start));
    }
}
