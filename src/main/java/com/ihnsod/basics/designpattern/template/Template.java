package com.ihnsod.basics.designpattern.template;

/**
 * 模板设计模式
 */
public class Template {
    public static void main(String[] args) {
        Customer customerSlow = new CustomerSlow();
        Customer customerQuick = new CustomerQuick();
        customerSlow.doEat();
        customerQuick.doEat();
    }
}

abstract class Customer {

    public abstract void preEat();

    public abstract void eat();

    public abstract void afterEat();

    public final void doEat() {
        this.preEat();
        this.eat();
        this.afterEat();
    }
}

class CustomerSlow extends Customer {

    @Override
    public void preEat() {
        System.err.println("吃的慢的人吃饭之前玩手机");
    }

    @Override
    public void eat() {
        System.err.println("慢速吃饭");
    }

    @Override
    public void afterEat() {
        System.err.println("吃过饭后直接走了");
    }
}

class CustomerQuick extends Customer {

    @Override
    public void preEat() {
        System.err.println("吃的快的人吃饭之前洗手");
    }

    @Override
    public void eat() {
        System.err.println("快速吃饭");
    }

    @Override
    public void afterEat() {
        System.err.println("吃过饭后收拾餐具");
    }
}