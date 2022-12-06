package com.zero.springframework.core.convert.converter;

import cn.hutool.core.lang.Assert;

import java.util.Objects;
import java.util.Set;

/**
 * @author zero
 * @description GenericConverter 通用转换接口
 * @date 2022/12/6 15:34
 */
public interface GenericConverter {
    Set<ConverterPair> getConverterTypes();

    Object convert(Object source, Class sourceType, Class targetType);

    final class ConverterPair{
        private final Class<?> sourceType;
        private final Class<?> targetType ;
        public ConverterPair(Class<?> sourceType, Class<?> targetType) {
            Assert.notNull(sourceType, "Source Type must not be null");
            Assert.notNull(targetType, "Target Type must not be null");
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public Class<?> getSourceType() {
            return sourceType;
        }

        public Class<?> getTargetType() {
            return targetType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ConverterPair that = (ConverterPair) o;
            return Objects.equals(sourceType, that.sourceType) && Objects.equals(targetType, that.targetType);
        }

        @Override
        public int hashCode() {
            return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
        }
    }
}
