package com.ihnsod.basics.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ihnsod
 * @create 2018/05/30
 **/
public class Demo1 {
    /**
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * <p>
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     */
    @Test
    public void test1() {
        int[] nums = {-1, 0, 1, 2, -1, -4};

        Arrays.sort(nums);

        int num = nums.length;
        int R = num - 1;

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            int L = i + 1;
            int t = nums[i] + nums[L] + nums[R];

            if (L <= R) {
                if (t < 0) {
                    L++;
                }
                if (t > 0) {
                    R--;
                }
                if (t == 0) {
                    list.add(new int[]{nums[i], nums[L], nums[R]});
                }
            } else {
                continue;
            }
        }

        for (int[] arr : list) {
            System.err.println(arr[0] + "," + arr[1] + "," + arr[2]);
        }

    }
}
