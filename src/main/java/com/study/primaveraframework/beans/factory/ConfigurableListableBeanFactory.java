package main.java.com.study.primaveraframework.beans.factory;

import main.java.com.study.primaveraframework.beans.BeanException;
import main.java.com.study.primaveraframework.beans.factory.config.AutowireCapableBeanFactory;
import main.java.com.study.primaveraframework.beans.factory.config.BeanDefinition;
import main.java.com.study.primaveraframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    void preInstantiateSingletons();
}
