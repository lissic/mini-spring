package com.zero.springframework.context.support;

import cn.hutool.core.util.XmlUtil;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zero.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author zero
 * @description AbstractXmlApplicationContext
 * @date 2022/5/26 15:34
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
