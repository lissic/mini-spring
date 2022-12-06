package com.zero.springframework.core.convert;

import com.sun.istack.internal.Nullable;

/**
 * @author zero
 * @description ConversionService 类型转换抽象接口
 * @date 2022/12/6 15:39
 */
public interface ConversionService {

    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    <T> T convert(Object source, Class<T> targetType);
}
