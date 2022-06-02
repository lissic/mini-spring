package com.zero.springframework.context;

import com.zero.springframework.beans.factory.HierarchicalBeanFactory;
import com.zero.springframework.beans.factory.ListableBeanFactory;
import com.zero.springframework.core.io.ResourceLoader;

/**
 * @author zero
 * @description ApplicationContext
 * @date 2022/5/26 15:17
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
