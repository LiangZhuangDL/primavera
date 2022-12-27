package main.java.com.study.primaveraframework.beans.factory.support;

import main.java.com.study.primaveraframework.beans.BeanException;
import main.java.com.study.primaveraframework.beans.factory.config.BeanDefinition;
import main.java.com.study.primaveraframework.beans.factory.config.BeanPostProcessor;
import main.java.com.study.primaveraframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的 Bean工厂基类，定义模板方法
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeanException{
        return this.doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object[] args) throws BeanException{
        return this.doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeanException{
        return (T) getBean(beanName);
    }

    protected abstract BeanDefinition getBeanDefinition(String name) throws BeanException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException;

    protected <T> T doGetBean(final String beanName, final Object[] args){
        Object bean = getSingleton(beanName);
        if(bean != null) return (T) bean;
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName, beanDefinition, args);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors(){
        return this.beanPostProcessors;
    }
}
