package com.ihnsod.basics.arithmetic.chapteronepractice;

import org.junit.Test;

public class Practice1 {


    public static void main(String[] args) {
        System.err.println(methodOne(6));

        System.err.println(methodTwo(2, 25));

        System.err.println(methodThree(24, 8));
    }

    /**
     * 求两个整数的最大公约数
     */
    @Test
    public static int methodThree(int p, int q) {
        if (q == 0) {
            return p;
        }
        int r = p % q;
        return methodThree(q, r);
    }


    /**
     * 写一个方法接受一个整形int[]数组和一个整数M作为参数并返回一个大小为M的数组
     * 其中第i个元素的值为整数i在数组中出现的次数,如果a[]中的值均在0-M-1之间返回数组中的所有元素之和应该和a.length相等
     */
    @Test
    public void method() {
        int[] a = {11, 22, 1, 1, 2, 2, 2, 2, 3, 3, 5, 5, 5};
        int M = 7;
        int[] rsArr = new int[M];
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 0 && a[i] <= rsArr.length) {
                rsArr[a[i]] += 1;
            }
        }

        for (int value : rsArr) {
            System.err.println(value);
        }
    }

    /**
     * 递归的练习
     */
    @Test
    public static String methodOne(int n) {
        if (n <= 0) {
            return "";
        } else {
            return methodOne(n - 3) + n + methodOne(n - 2) + n;
        }
    }

    /**
     * 求两个数的最大公约数
     */
    @Test
    public static int methodTwo(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (b % 2 == 0) {
            return methodTwo(a + a, b / 2);
        }
        return methodTwo(a + a, b / 2) + a;
    }

    /**
     * 求一个数字是否为素数
     */
    @Test
    public static boolean methodFour(int value) {
        if (value >= 0 && value < 2 || value % 2 == 0) {
            return false;
        }
        if (value == 2) {
            return true;
        }

        for (int i = 3; i < Math.sqrt(value); i += 2) {
            if (value % i == 0) {
                return true;
            }
        }
        return false;
    }
}
