package com.ihnsod.basics.collection;

import org.junit.Test;

import java.util.Stack;

/**
 * @author: Ihnsod
 * @create: 2019/05/01 16:51
 **/
public class StackExample {

    @Test
    public void structure(){
        // 栈 先进后出  底层使用的动态数组进行存储数据 和list差不多
        Stack<String> strings = new Stack<>();

        // 压栈 返回压栈的值
        String hello = strings.push("hello");
        String world = strings.push("world");
        String nullItem = strings.push(null);
        String nullItem1 = strings.push(null);
        // 弹栈
        String pop = strings.pop();
        System.out.println(pop);
        // 弹栈后元素个数减少一个
        System.out.println(strings.size());
        // peek方法只查看栈顶元素 不会减小栈的大小
        String peek = strings.peek();
        System.out.println(peek);
        System.out.println(strings.size());
    }
}
