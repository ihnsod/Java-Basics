package com.ihnsod.basics.proxy.dynamicproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: Ihnsod
 * @create: 2018/11/25 23:02
 **/
public class ProxyClient implements InvocationHandler {

    private Object obj;

    public ProxyClient(Object obj) {
        this.obj = obj;
    }

    public static Object getProxy(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new ProxyClient(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(obj, args);
        after();
        return invoke;
    }

    private void before() {
        System.err.println("前置通知");
    }

    private void after() {
        System.err.println("后置通知");
    }
}
