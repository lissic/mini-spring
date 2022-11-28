package com.zero.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @author zero
 * @description Component
 * @date 2022/11/28 16:43
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";
}
