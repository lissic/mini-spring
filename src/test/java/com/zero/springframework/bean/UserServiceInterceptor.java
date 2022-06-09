package com.zero.springframework.bean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author zero
 * @description UserServiceInterceptor
 * @date 2022/6/9 13:52
 */
public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return methodInvocation.proceed();
        } finally {
            System.out.println("执行了interceptor方法");
        }
    }
}
