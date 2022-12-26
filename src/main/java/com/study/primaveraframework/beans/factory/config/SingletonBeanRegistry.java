package main.java.com.study.primaveraframework.beans.factory.config;


public interface SingletonBeanRegistry {

    /**
     * 返回在给定名称下注册（原始）单例对象
     * @param name 要查找的Bean名称
     * @return 返回注册的单例对象
     */
    Object getSingleton(String name);

    /**
     * 注册单例对象
     * @param beanName Bean对象名称
     * @param singletonObject Bean对象
     */
    void registrySingleton(String beanName, Object singletonObject);
}
