package com.zero.springframework.context;

import java.util.EventObject;

/**
 * @author zero
 * @description ApplicationEvent 事件功能抽象类
 * @date 2022/6/2 14:05
 */
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
