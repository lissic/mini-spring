package com.zero.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author zero
 * @description Advisor
 * @date 2022/6/10 10:35
 */
public interface Advisor {
    Advice getAdvice();
}
