package com.zero.springframework.context.support;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zero.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.zero.springframework.beans.factory.config.BeanPostProcessor;
import com.zero.springframework.context.ConfigurableApplicationContext;
import com.zero.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author zero
 * @description AbstractApplicationContext
 * @date 2022/5/26 15:20
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // 1、创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();
        // 2、获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 3、在bean实例化之前，之前BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        // 4、BeanPostProcessor需要提前于其他bean对象实例之前执行注册
        registerBeanPostProcessors(beanFactory);
        // 5、提前实例化单例对象
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor postProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(postProcessor);
        }
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }
}
