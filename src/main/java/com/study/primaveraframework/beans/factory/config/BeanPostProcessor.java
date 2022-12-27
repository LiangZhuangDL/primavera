package main.java.com.study.primaveraframework.beans.factory.config;

import main.java.com.study.primaveraframework.beans.BeanException;

public interface BeanPostProcessor {

    /**
     * 在Bean对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeanException;

    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessorAfterInitialization(Object bean, String beanName) throws BeanException;
}
