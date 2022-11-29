package com.zero.springframework.context.support;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.zero.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.zero.springframework.beans.factory.config.BeanPostProcessor;
import com.zero.springframework.context.ApplicationEvent;
import com.zero.springframework.context.ApplicationListener;
import com.zero.springframework.context.ConfigurableApplicationContext;
import com.zero.springframework.context.event.ApplicationEventMulticaster;
import com.zero.springframework.context.event.ContextCloseEvent;
import com.zero.springframework.context.event.ContextRefreshedEvent;
import com.zero.springframework.context.event.SimpleApplicationEventMulticaster;
import com.zero.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @author zero
 * @description AbstractApplicationContext
 * @date 2022/5/26 15:20
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        // 1、创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();
        // 2、获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 3、添加ApplicationContextAwareProcessor，让继承自ApplicationContextAware的bean对象都能感知所属的ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        // 4、在bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        // 5、BeanPostProcessor需要提前于其他bean对象实例之前执行注册
        registerBeanPostProcessors(beanFactory);
        // 6、初始化广播器
        initApplicationEventMulticaster();
        // 7、注册事件监听器
        registerListeners();
        // 8、提前实例化单例对象
        beanFactory.preInstantiateSingletons();
        // 9、发布容器刷新完成事件
        finishRefresh();
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    private void registerListeners() {
        try {
            Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
            for (ApplicationListener listener : applicationListeners) {
                applicationEventMulticaster.addApplicationListener(listener);
            }
        } catch (BeansException e) {
            e.printStackTrace();
        }

    }

    private void initApplicationEventMulticaster() {
        // 2、获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        try {
            // 发布容器关闭事件
            publishEvent(new ContextCloseEvent(this));
            // 执行销毁单例bean的回调方法
            getBeanFactory().destroySingletons();
        } catch (BeansException e) {
            e.printStackTrace();
        }
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

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }
}
