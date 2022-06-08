package com.zero.springframework.aop;

/**
 * @author zero
 * @description ClassFilter
 * @date 2022/6/8 14:38
 */
public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
