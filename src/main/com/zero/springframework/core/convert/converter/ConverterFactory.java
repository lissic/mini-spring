package com.zero.springframework.core.convert.converter;

/**
 * @author zero
 * @description ConvertFactory 类型转换工厂
 * @date 2022/12/6 15:30
 */
public interface ConverterFactory<S, R> {

    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
