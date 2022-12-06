package com.zero.springframework.core.convert.converter;

/**
 * @author zero
 * @description ConverterRegistry 类型转换注册接口
 * @date 2022/12/6 15:32
 */
public interface ConverterRegistry {
    void addConverter(Converter<?, ?> converter);
    void addConverter(GenericConverter converter);
    void addConverterFactory(ConverterFactory<?, ?> convertFactory);
}
