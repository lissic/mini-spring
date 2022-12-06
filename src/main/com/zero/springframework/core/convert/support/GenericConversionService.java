package com.zero.springframework.core.convert.support;

import com.zero.springframework.core.convert.ConversionService;
import com.zero.springframework.core.convert.converter.ConverterFactory;
import com.zero.springframework.core.convert.converter.Converter;
import com.zero.springframework.core.convert.converter.ConverterRegistry;
import com.zero.springframework.core.convert.converter.GenericConverter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author zero
 * @description GenericConversionService
 * @date 2022/12/6 15:42
 */
public class GenericConversionService implements ConversionService, ConverterRegistry {
    private Map<GenericConverter.ConverterPair, GenericConverter> converters = new HashMap<>();

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        GenericConverter converter = getConverter(sourceType, targetType);
        return converter!=null;
    }

    protected GenericConverter getConverter(Class<?> sourceType, Class<?> targetType) {
        List<Class<?>> sourceCandidates = getClassHierarchy(sourceType);
        List<Class<?>> targetCandidates = getClassHierarchy(targetType);
        for (Class<?> sourceCandidate : sourceCandidates) {
            for (Class<?> targetCandidate : targetCandidates) {
                GenericConverter.ConverterPair converterPair = new GenericConverter.ConverterPair(sourceCandidate, targetCandidate);
                GenericConverter converter = converters.get(converterPair);
                if(converter!=null) {
                    return converter;
                }
            }
        }
        return null;
    }

    private List<Class<?>> getClassHierarchy(Class<?> clazz) {
        List<Class<?>> hierarchy = new ArrayList<>();
        while (clazz != null) {
            hierarchy.add(clazz);
            clazz = clazz.getSuperclass();
        }
        return hierarchy;
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        Class<?> sourceType = source.getClass();
        GenericConverter converter = getConverter(sourceType, targetType);
        return (T) converter.convert(source, sourceType, targetType);
    }

    @Override
    public void addConverter(Converter<?, ?> converter) {
        GenericConverter.ConverterPair typeInfo = getRequiredTypeInfo(converter);
        ConverterAdapter converterAdapter = new ConverterAdapter(typeInfo, converter);
        for (GenericConverter.ConverterPair converterType : converterAdapter.getConverterTypes()) {
            converters.put(converterType, converterAdapter);
        }
    }

    private GenericConverter.ConverterPair getRequiredTypeInfo(Object object) {
        Type[] types = object.getClass().getGenericInterfaces();
        ParameterizedType parameterized = (ParameterizedType) types[0];
        Type[] actualTypeArguments = parameterized.getActualTypeArguments();
        Class sourceType = (Class) actualTypeArguments[0];
        Class targetType = (Class) actualTypeArguments[1];
        return new GenericConverter.ConverterPair(sourceType, targetType);
    }

    @Override
    public void addConverter(GenericConverter converter) {
        for (GenericConverter.ConverterPair converterType : converter.getConverterTypes()) {
            converters.put(converterType, converter);
        }
    }

    @Override
    public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {
        GenericConverter.ConverterPair typeInfo = getRequiredTypeInfo(converterFactory);
        ConverterFactoryAdapter converterFactoryAdapter = new ConverterFactoryAdapter(typeInfo, converterFactory);
        for (GenericConverter.ConverterPair converterType : converterFactoryAdapter.getConverterTypes()) {
            converters.put(converterType, converterFactoryAdapter);
        }
    }

    private final class ConverterAdapter implements GenericConverter{
        private final ConverterPair typeInfo;
        private final Converter<Object, Object> converter;

        public ConverterAdapter(ConverterPair typeInfo, Converter<?, ?> converter) {
            this.typeInfo = typeInfo;
            this.converter = (Converter<Object, Object>) converter;
        }

        @Override
        public Set<ConverterPair> getConverterTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        public Object convert(Object source, Class sourceType, Class targetType) {
            return converter.convert(source);
        }
    }

    private final class ConverterFactoryAdapter implements GenericConverter{
        private final ConverterPair typeInfo;
        private final ConverterFactory<Object, Object> converterFactory;

        public ConverterFactoryAdapter(ConverterPair typeInfo, ConverterFactory<?, ?> converterFactory) {
            this.typeInfo = typeInfo;
            this.converterFactory = (ConverterFactory<Object, Object>) converterFactory;
        }

        @Override
        public Set<ConverterPair> getConverterTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        public Object convert(Object source, Class sourceType, Class targetType) {
            return converterFactory.getConverter(targetType).convert(source);
        }
    }
}
