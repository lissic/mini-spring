package com.zero.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import com.zero.springframework.beans.BeansException;
import com.zero.springframework.beans.PropertyValue;
import com.zero.springframework.beans.PropertyValues;
import com.zero.springframework.beans.factory.*;
import com.zero.springframework.beans.factory.config.*;
import com.zero.springframework.core.convert.ConversionService;
import org.omg.CORBA.ObjectHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author zero
 * @description AbstractAutowireCapableBeanFactory 实现创建Bean的具体逻辑
 * @date 2022/4/18 11:52
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        // 判断是否返回代理Bean对象
        Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
        if (null != bean){
            return bean;
        }
        return doCreateBean(beanName, beanDefinition, args);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            // 实例化Bean
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 处理循环依赖，将实例化后的Bean对象提前放入缓存中暴露出来
            if (beanDefinition.isSingleton()) {
                Object finalBean = bean;
                addSingletonFactory(beanName, ()-> getEarlyBeanReference(beanName, beanDefinition, finalBean));
            }
            // 实例化后判断
            boolean continueWithPropertyPopulation = applyBeanPostProcessorAfterInstantiation(beanName, bean);
            if (!continueWithPropertyPopulation) {
                return bean;
            }
            // 在设置Bean对象的属性之前，允许BeanPostProcessor接口修改属性值
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
            // 属性填充
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行bean的初始化方法和beanPostProcessor的前置和后置方法
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception | BeansException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 注册实现了DisposableBean接口的bean对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        // 添加到缓存池, 判断SCOPE_SINGLETON、SCOPE_PROTOTYPE
        Object exposedObject = bean;
        if (beanDefinition.isSingleton()) {
            // 获取代理对象
            exposedObject = getSingleton(beanName);
            registerSingleton(beanName, exposedObject);
        }
        return bean;
    }

    protected Object getEarlyBeanReference(String beanName, BeanDefinition beanDefinition, Object bean) throws BeansException {
        Object exposedObject = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                exposedObject = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).getEarlyBeanReference(exposedObject, beanName);
                if (null == exposedObject) {
                    return exposedObject;
                }
            }
        }
        return exposedObject;
    }

    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = applyBeanPostProcessorsBeforeInitialization(beanDefinition.getBeanClass(), beanName);
        if (null != bean) {
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }
        return bean;
    }

    protected Object applyBeanPostProcessorsBeforeInitialization(Class<?> beanClass, String beanName) throws BeansException {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if(beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = beanPostProcessor.postProcessBeforeInitialization(beanClass, beanName);
                if(null != result) return result;
            }
        }
        return null;
    }

    protected void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if(beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
                if (null != pvs) {
                    for (PropertyValue propertyValue : pvs.getPropertyValues()) {
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 非单例的bean不执行销毁方法
        if (!beanDefinition.isSingleton()) {
            return;
        }
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue pv : propertyValues.getPropertyValues()) {
                String name = pv.getName();
                Object value = pv.getValue();
                if (value instanceof BeanReference) {
                    // 如果依赖的属性是对象，则创建对象
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 类型转换
                else {
                    Class<?> sourceType = value.getClass();
                    Class<?> targetType = (Class<?>)TypeUtil.getFieldType(bean.getClass(), name);
                    ConversionService conversionService = getConversionService();
                    if(conversionService != null) {
                        if (conversionService.canConvert(sourceType, targetType)) {
                            value = conversionService.convert(value, targetType);
                        }
                    }
                }
                // 反射设置属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }

        } catch (Exception e) {
            throw new BeansException("Error setting property values: " + beanName);
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) throws BeansException {
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        // 循环找到适配的构造函数
        for (Constructor<?> ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) throws BeansException {
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }

        // 执行BPP的before方法
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        try {
            // 调用对象初始化方法
            invokeInitMethod(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        // 执行BPP的after方法
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethod(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1、实现接口 InitializingBean
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
        // 2、配置信息init-method
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new BeanException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current)  return result;
            result = current;
        }
        return result;
    }

    /**
     *  Bean实例化后对于返回false 的对象，不在执行后续设置bean对象属性的操作
     * @param beanName 对象名称
     * @param bean 对象
     * @return boolean
     */
    private boolean applyBeanPostProcessorAfterInstantiation(String beanName, Object bean) {
        boolean continueWithPropertyPopulation = true;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor = (InstantiationAwareBeanPostProcessor) beanPostProcessor;
                if (!instantiationAwareBeanPostProcessor.postProcessAfterInstantiation(bean, beanName)) {
                    continueWithPropertyPopulation = false;
                    break;
                }
            }
        }
        return continueWithPropertyPopulation;
    }


}
