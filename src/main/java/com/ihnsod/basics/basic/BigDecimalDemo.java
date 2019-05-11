package com.ihnsod.basics.basic;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * BigDecimal类常用于金额的计算
 */
public class BigDecimalDemo {
    public static void main(String[] args) {
        Double a = 2.50;
        Double b = 2.41;
        //double 类型的数据加法操作不会出现不准去的问题，减法操作会出现不准确的问题
        System.err.println(a - b);
        System.err.println(a + b);
        BigDecimal b1 = new BigDecimal("2.50");
        BigDecimal b2 = new BigDecimal("2.41");
        System.err.println(b1.subtract(b2));
    }

    @Test
    public void test() {
        BigDecimal bigDecimal = new BigDecimal("55.99767676");
        BigDecimal bigDecimal1 = new BigDecimal("67.02");

        /**
         * 加法(MathContext.DECIMAL64方法是确定计算的精度)
         * MathContext.DECIMAL32 代表精度最高为7位
         * MathContext.DECIMAL64 代表精度最高为16位
         * MathContext.DECIMAL128 代表精度最高为34位
         */

        bigDecimal.add(bigDecimal1);
        bigDecimal.add(bigDecimal, MathContext.DECIMAL64);

        System.err.println(bigDecimal.add(bigDecimal1, MathContext.DECIMAL32));
        //减法
        bigDecimal.subtract(bigDecimal1);
        bigDecimal.subtract(bigDecimal, MathContext.DECIMAL64);
        //乘法
        bigDecimal.multiply(bigDecimal);
        bigDecimal.multiply(bigDecimal, MathContext.DECIMAL64);
        //除法
        bigDecimal.divide(bigDecimal1);
        bigDecimal.divide(bigDecimal1,MathContext.DECIMAL32);
        bigDecimal.divide(bigDecimal1,BigDecimal.ROUND_HALF_EVEN);
        bigDecimal.divide(bigDecimal1,3,BigDecimal.ROUND_FLOOR);
        bigDecimal.divide(bigDecimal1,3,BigDecimal.ROUND_HALF_UP);

    }
}
