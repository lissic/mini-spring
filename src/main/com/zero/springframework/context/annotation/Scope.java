package com.zero.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @author zero
 * @description Scope 作用域注解
 * @date 2022/11/28 16:41
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    String value() default "singleton";
}
