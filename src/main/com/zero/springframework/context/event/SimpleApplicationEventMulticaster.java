package com.zero.springframework.context.event;

import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.BeanFactory;
import com.zero.springframework.context.ApplicationEvent;
import com.zero.springframework.context.ApplicationListener;

/**
 * @author zero
 * @description SimpleApplicationEventMulticaster
 * @date 2022/6/2 14:30
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        try {
            for (ApplicationListener listener : getApplicationListeners(event)) {
                listener.onApplicationEvent(event);
            }
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
