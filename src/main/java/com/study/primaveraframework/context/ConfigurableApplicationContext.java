package main.java.com.study.primaveraframework.context;

import main.java.com.study.primaveraframework.beans.BeanException;

public interface ConfigurableApplicationContext extends ApplicationContext{

    void refresh() throws BeanException;
}
