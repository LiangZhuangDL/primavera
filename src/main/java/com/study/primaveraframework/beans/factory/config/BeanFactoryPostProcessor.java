package main.java.com.study.primaveraframework.beans.factory.config;

import main.java.com.study.primaveraframework.beans.BeanException;
import main.java.com.study.primaveraframework.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {

    /**
     * 在所有的BeanDefinition加载完成后，且将Bean对象实例化之前，提供修改BeanDefinition属性的机制
     * @param configurableListableBeanFactory
     * @throws BeanException
     */
    void postProcessorBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeanException;
}
