package com.zero.springframework.context.event;

/**
 * @author zero
 * @description ContextRefreshedEvent
 * @date 2022/6/2 14:08
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
