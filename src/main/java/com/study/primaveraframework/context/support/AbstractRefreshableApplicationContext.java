package main.java.com.study.primaveraframework.context.support;

import main.java.com.study.primaveraframework.beans.BeanException;
import main.java.com.study.primaveraframework.beans.factory.ConfigurableListableBeanFactory;
import main.java.com.study.primaveraframework.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;


    @Override
    protected void refreshBeanFactory() throws BeanException {
        DefaultListableBeanFactory defaultListableBeanFactory = createBeanFactory();
        loadBeanDefinitions(defaultListableBeanFactory);
        this.beanFactory = defaultListableBeanFactory;

    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory(){
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
}
