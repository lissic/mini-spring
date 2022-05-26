package com.zero.springframework.context.support;

import com.zero.springframework.beans.BeansException;

/**
 * @author zero
 * @description ClassPathXmlApplicationContext
 * @date 2022/5/26 16:29
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{
    private String[] configLocations;

    public ClassPathXmlApplicationContext(String configLocation) throws BeansException{
        this(new String[]{configLocation});
    }


    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    public String[] getConfigLocations() {
        return configLocations;
    }
}
