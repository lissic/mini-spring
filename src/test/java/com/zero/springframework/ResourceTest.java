package com.zero.springframework;

import cn.hutool.core.io.IoUtil;
import com.zero.springframework.bean.UserService;
import com.zero.springframework.bean.UserService2;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.factory.BeanFactory;
import com.zero.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.zero.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.zero.springframework.core.io.DefaultResourceLoader;
import com.zero.springframework.core.io.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zero
 * @description ResourceTest
 * @date 2022/5/25 17:14
 */
public class ResourceTest {
    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:import.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/lissic/mini-spring/blob/main/src/test/resources/import.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/import.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_xml() throws BeansException {
        // 1、初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2、读取配置文件
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        // 3、获取对象
        UserService2 userService = (UserService2) beanFactory.getBean("userService", UserService2.class);
        userService.queryUserInfo();

    }
}
