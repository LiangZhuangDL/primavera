package main.java.com.study.primaveraframework.beans.factory;

import main.java.com.study.primaveraframework.beans.BeanException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按照类型返回Bean实例
     * @param type
     * @return
     * @param <T>
     * @throws BeanException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException;

    /**
     * 返回所有已注册的实例名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
