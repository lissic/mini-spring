package com.zero.springframework.aop;

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
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
