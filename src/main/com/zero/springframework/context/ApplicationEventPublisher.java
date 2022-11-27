package com.zero.springframework.context;

/**
 * @author zero
 * @description ApplicationEventPublisher 事件发布功能接口
 * @date 2022/6/2 14:25
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
