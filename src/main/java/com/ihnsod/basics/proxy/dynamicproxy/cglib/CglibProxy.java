package com.ihnsod.basics.proxy.dynamicproxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: Ihnsod
 * @create: 2018/12/01 01:24
 **/
public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.err.println("sayHello before !");
        Object result = methodProxy.invokeSuper(o, objects);
        System.err.println("byebye after");
        return result;
    }
}
