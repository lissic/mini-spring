package com.zero.springframework.context.support;

import com.sun.istack.internal.Nullable;
import com.zero.springframework.beans.factory.FactoryBean;
import com.zero.springframework.beans.factory.InitializingBean;
import com.zero.springframework.core.convert.ConversionService;
import com.zero.springframework.core.convert.converter.Converter;
import com.zero.springframework.core.convert.converter.ConverterFactory;
import com.zero.springframework.core.convert.converter.ConverterRegistry;
import com.zero.springframework.core.convert.converter.GenericConverter;
import com.zero.springframework.core.convert.support.DefaultConversionService;
import com.zero.springframework.core.convert.support.GenericConversionService;

import java.util.Set;

/**
 * @author zero
 * @description ConversionServiceFactoryBean 提供创建ConversionService工厂
 * @date 2022/12/6 16:45
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {
    @Nullable
    private Set<?> converters;
    @Nullable
    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if(converters instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if(converter instanceof Converter<?,?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if(converter instanceof ConverterFactory<?,?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "converter,  ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }
}
