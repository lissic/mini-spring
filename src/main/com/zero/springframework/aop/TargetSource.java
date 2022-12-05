package com.zero.springframework.aop;

import com.zero.springframework.util.ClassUtils;

/**
 * @author zero
 * @description TargetSource
 * @date 2022/6/8 14:47
 */
public class TargetSource {
    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz)? clazz.getSuperclass():clazz;
        return clazz.getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
