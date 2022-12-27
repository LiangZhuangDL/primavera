package main.java.com.study.primaveraframework.context.support;

import main.java.com.study.primaveraframework.beans.BeanException;
import main.java.com.study.primaveraframework.beans.factory.ConfigurableListableBeanFactory;
import main.java.com.study.primaveraframework.beans.factory.config.BeanFactoryPostProcessor;
import main.java.com.study.primaveraframework.beans.factory.config.BeanPostProcessor;
import main.java.com.study.primaveraframework.context.ConfigurableApplicationContext;
import main.java.com.study.primaveraframework.core.io.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() throws BeanException {
        this.refreshBeanFactory();
        ConfigurableListableBeanFactory configurableListableBeanFactory = getBeanFactory();
        this.invokeBeanFactoryPostProcessors(configurableListableBeanFactory);
        this.registerBeanPostProcessors(configurableListableBeanFactory);
        configurableListableBeanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory() throws BeanException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public Object getBean(String name) throws BeanException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeanException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeanException {
        return getBeanFactory().getBean(beanName, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
}
