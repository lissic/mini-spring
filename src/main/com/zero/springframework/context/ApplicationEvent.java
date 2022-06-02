package com.zero.springframework.context;

import java.util.EventObject;

/**
 * @author zero
 * @description ApplicationEvent
 * @date 2022/6/2 14:05
 */
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
