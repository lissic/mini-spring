package com.zero.springframework.context.event;

/**
 * @author zero
 * @description ContextRefreshedEvent 上下文刷新事件
 * @date 2022/6/2 14:08
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
