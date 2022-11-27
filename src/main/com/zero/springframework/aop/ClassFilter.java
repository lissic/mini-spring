package com.zero.springframework.aop;

/**
 * @author zero
 * @description ClassFilter 类匹配接口，用于找到给定的接口和目标类
 * @date 2022/6/8 14:38
 */
public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
