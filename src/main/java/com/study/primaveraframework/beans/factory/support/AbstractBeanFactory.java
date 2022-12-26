package main.java.com.study.primaveraframework.beans.factory.support;

import main.java.com.study.primaveraframework.beans.BeanException;
import main.java.com.study.primaveraframework.beans.factory.BeanFactory;
import main.java.com.study.primaveraframework.beans.factory.config.BeanDefinition;

/**
 * 抽象的 Bean工厂基类，定义模板方法
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeanException{
        Object bean = getSingleton(name);
        if(bean != null){
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return create(name, beanDefinition);
    }

    @Override
    public Object getBean(String name, Object[] args) throws BeanException{
        Object bean = getSingleton(name);
        if(bean != null){
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return create(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeanException;

    protected abstract Object create(String beanName, BeanDefinition beanDefinition) throws BeanException;

    protected abstract Object create(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException;
}
