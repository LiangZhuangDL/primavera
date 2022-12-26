package main.java.com.study.primaveraframework.beans.factory;

import main.java.com.study.primaveraframework.beans.BeanException;

public interface BeanFactory {
    /**
     * 返回Bean的实例对象
     * @param name 要检索的Bean名称
     * @return 实例化的Bean对象
     * @throws BeanException 不能获取Bean对象，则抛出异常
     */
    Object getBean(String name) throws BeanException;

    /**
     * 返回含构造函数的Bean实例对象
     * @param name 要检索的Bean名称
     * @param args 构造函数入参
     * @return 实例化的Bean对象
     * @throws BeanException 不能获取Bean对象，则抛出异常
     */
    Object getBean(String name, Object... args)throws BeanException;
}
