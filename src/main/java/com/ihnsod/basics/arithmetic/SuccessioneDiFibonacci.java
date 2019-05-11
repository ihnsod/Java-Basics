package com.ihnsod.basics.arithmetic;

import org.junit.Test;

/**
 * 斐波那契数列的实现
 */
public class SuccessioneDiFibonacci {

    public static void main(String args[]) {
//        System.err.println(method(12));
//        System.err.println(methodOne(12));
//        System.err.println(methodTwo(12));
        System.err.println(methodThree(43));
    }

    /**
     * 递归法实现
     */
    public static int method(int index) {

        if (index <= 2) {
            return 1;
        }
        return method(index - 1) + method(index - 2);
    }

    /**
     * 非递归方法实现 构建数组方式
     */
    public static int methodOne(int index) {
        int[] arr = {1, 1, index};
        for (int i = 2; i < index; i++) {
            arr[2] = arr[0] + arr[1];
            arr[0] = arr[1];
            arr[1] = arr[2];
        }
        return arr[2];
    }

    /**
     * 非递归方法实现 直接定义整数方式
     */
    public static int methodTwo(int index) {
        if (index >= 0 && index < 2) {
            return 1;
        }
        int a = 1, b = 1, c = 0;
        for (int i = 2; i < index; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 将一个正整数转为一个String类型的二进制值
     */
    public static String methodThree(int value) {
        String s = "";
        for (int n = value; n > 0; n /= 2) {
            //先除的在右侧，s要在右侧加
            s = n % 2 + s;
        }
        return s;
    }

    /**
     * char类型的加减
     */
    @Test
    public void methodFour() {
        char a = 'b';
        char b = 'c';
        System.err.println(a + b);//197 会是对应的ascII码
        System.err.println((char) (a + 'd'));//奇怪的符号
    }
}
