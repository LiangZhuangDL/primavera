package main.java.com.study.primaveraframework.context.support;

import main.java.com.study.primaveraframework.beans.factory.support.DefaultListableBeanFactory;
import main.java.com.study.primaveraframework.beans.factory.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = this.getConfigLocations();
        if(null != configLocations){
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
