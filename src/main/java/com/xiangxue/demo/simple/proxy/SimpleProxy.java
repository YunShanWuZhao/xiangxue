package com.xiangxue.demo.simple.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SimpleProxy implements InvocationHandler {

    private Object workModule;

    public Object getWorkModule() {
        return workModule;
    }

    public void setWorkModule(Object workModule) {
        this.workModule = workModule;
    }

    public Object newInstance(){
        return Proxy.newProxyInstance(workModule.getClass().getClassLoader(), workModule.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("do something before...");
        method.invoke(proxy, args);
        System.out.println("do something after...");
        return null;
    }
}
