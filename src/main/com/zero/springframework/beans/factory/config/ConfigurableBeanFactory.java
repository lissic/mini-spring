package com.zero.springframework.beans.factory.config;

import com.sun.istack.internal.Nullable;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.HierarchicalBeanFactory;
import com.zero.springframework.core.convert.ConversionService;
import com.zero.springframework.core.convert.converter.Converter;
import com.zero.springframework.util.StringValueResolver;

/**
 * @author zero
 * @description ConfigurableBeanFactory
 * @date 20r2/5/26 15:37
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry{
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons() throws BeansException;

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);

    void setConversionService(ConversionService conversionService);

    @Nullable
    ConversionService getConversionService();
}
