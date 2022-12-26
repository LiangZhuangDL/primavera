package main.java.com.study.primaveraframework.beans.factory.support;

import main.java.com.study.primaveraframework.core.io.DefaultResourceLoader;
import main.java.com.study.primaveraframework.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private final BeanDefinitionRegistry beanDefinitionRegistry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry){
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry(){
        return this.beanDefinitionRegistry;
    }

    @Override
    public ResourceLoader getResourceLoader(){
        return this.resourceLoader;
    }
}
