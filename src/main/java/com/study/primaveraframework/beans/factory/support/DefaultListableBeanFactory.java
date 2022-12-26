package main.java.com.study.primaveraframework.beans.factory.support;

import main.java.com.study.primaveraframework.beans.BeanException;
import main.java.com.study.primaveraframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的Bean工厂实现类
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

    private Map<String ,Object> beanDefinitions = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String name) throws BeanException {
        BeanDefinition beanDefinition = getBeanDefinition(name);
        if(beanDefinition == null) throw new BeanException("No bean named [" + name + "] is defined.");
        return beanDefinition;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitions.keySet().toArray(new String[0]);
    }

    @Override
    public void registryBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitions.put(name, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitions.containsKey(beanName);
    }
}
