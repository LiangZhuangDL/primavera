package main.java.com.study.primaveraframework.beans.factory.support;

import main.java.com.study.primaveraframework.beans.BeanException;
import main.java.com.study.primaveraframework.beans.factory.config.BeanDefinition;

/**
 * Bean定义注册接口
 */
public interface BeanDefinitionRegistry {

    void registryBeanDefinition(String name, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String beanName);

    BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    String[] getBeanDefinitionNames();
}
