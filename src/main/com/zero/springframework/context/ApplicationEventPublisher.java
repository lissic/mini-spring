package com.zero.springframework.context;

/**
 * @author zero
 * @description ApplicationEventPublisher
 * @date 2022/6/2 14:25
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
