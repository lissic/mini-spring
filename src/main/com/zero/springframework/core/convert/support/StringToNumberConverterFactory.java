package com.zero.springframework.core.convert.support;

import cn.hutool.core.util.NumberUtil;
import com.sun.istack.internal.Nullable;
import com.zero.springframework.core.convert.converter.Converter;
import com.zero.springframework.core.convert.converter.ConverterFactory;
import com.zero.springframework.util.NumberUtils;

/**
 * @author zero
 * @description StringToNumberConverterFactory
 * @date 2022/12/6 16:07
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private final class StringToNumber<T extends Number> implements Converter<String, T> {
        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Nullable
        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }
}
