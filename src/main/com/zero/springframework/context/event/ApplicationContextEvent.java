package com.zero.springframework.context.event;

import com.zero.springframework.context.ApplicationContext;
import com.zero.springframework.context.ApplicationEvent;

/**
 * @author zero
 * @description ApplicationContextEvent
 * @date 2022/6/2 14:07
 */
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
