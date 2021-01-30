package com.xiangxue.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogInterceptor {

    @Pointcut("execution(com.xiangxue.demo.aop.*(..))")
    public void logTracePoint(){}

    @Before(value = "logTracePoint")
    public void logTrace(){
        System.out.println("try to log the request trace...");
    }

}
