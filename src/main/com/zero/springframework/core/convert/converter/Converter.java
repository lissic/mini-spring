package com.zero.springframework.core.convert.converter;

/**
 * @author zero
 * @description Converter 类型转换接口
 * @date 2022/12/6 15:19
 */
public interface Converter<S, T> {
    T convert(S source);
}
