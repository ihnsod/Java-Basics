package com.ihnsod.basics.basic;

import org.junit.Test;

/**
 * @author: Ihnsod
 * @create: 2018/12/29 17:31
 **/
public class ClassLoaderDemo {
    @Test
    public void Test() throws ClassNotFoundException {
//        System.err.println(ClassLoader.getSystemClassLoader().getName());

        Class<?> aClass = Class.forName("java.lang.String");

        Class<?> aClass1 = String.class.getClassLoader().loadClass("java.lang.String");

        System.err.println("aClass="+aClass.getName()+"---"+"aClass1="+aClass1.getName());

    }
}
