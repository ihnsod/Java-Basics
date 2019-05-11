package com.ihnsod.basics.designpattern.strategypattern;

import java.math.BigDecimal;

/**
 * 策略模式
 * 策略模式是
 */
public class StrategyPattern {
    public static void main(String[] args) {
        MemberStrategy memberStrategy = new IntermediateMemberStrategy();

        Customer customer = new Customer(memberStrategy);

        BigDecimal price = customer.getPrice(new BigDecimal("300.68"));
        System.err.println(price);
    }
}

interface MemberStrategy {
    BigDecimal calcPrice(BigDecimal bigDecimal);
}

class PrimaryMemberStrategy implements MemberStrategy {

    @Override
    public BigDecimal calcPrice(BigDecimal bigDecimal) {
        System.err.println("普通会员，享受折扣。");
        return bigDecimal;
    }
}

class IntermediateMemberStrategy implements MemberStrategy {

    @Override
    public BigDecimal calcPrice(BigDecimal bigDecimal) {
        System.err.println("中级会员，享受9折优惠。");
        return bigDecimal.multiply(new BigDecimal("0.9"));
    }
}

class AdvancedMemberStrategy implements MemberStrategy {

    @Override
    public BigDecimal calcPrice(BigDecimal bigDecimal) {
        System.err.println("高级会员,享受8折优惠。");
        return bigDecimal.multiply(new BigDecimal("0.8"));
    }
}

class Customer {
    private MemberStrategy memberStrategy;

    public Customer(MemberStrategy memberStrategy) {
        this.memberStrategy = memberStrategy;
    }

    public BigDecimal getPrice(BigDecimal bigDecimal) {
        return this.memberStrategy.calcPrice(bigDecimal);
    }
}
