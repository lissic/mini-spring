package com.zero.springframework.context.event;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.BeanFactory;
import com.zero.springframework.beans.factory.BeanFactoryAware;
import com.zero.springframework.context.ApplicationEvent;
import com.zero.springframework.context.ApplicationListener;
import com.zero.springframework.util.ClassUtils;

import java.beans.Beans;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author zero
 * @description AbstractApplicationEventMulticaster 注册监听器和发布事件的广播器
 * @date 2022/6/2 14:14
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    private final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) throws BeansException {
        LinkedHashSet<ApplicationListener> allListeners = new LinkedHashSet<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    /**
     * 监听器是否对该事件感兴趣
     * @param listener 监听器
     * @param event 事件
     * @return boolean
     * @throws BeansException 异常
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) throws BeansException {
        Class<? extends ApplicationListener> listenerClass = listener.getClass();

        // 根据CglibSubclassingInstantiationStrategy 和 SimpleInstantiationStrategy不同的实例化类型，需要判断后获取目标class
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }

        // 判断eventClassName对象表示的类或接口与指定的event.getClass参数所表示的类或接口是否相同
        // 或者是否是其超接口
        // isAssignableFrom用来判断自雷和弗雷的关系，或者接口的实现类和接口的关系
        return eventClassName.isAssignableFrom(event.getClass());
    }

}
