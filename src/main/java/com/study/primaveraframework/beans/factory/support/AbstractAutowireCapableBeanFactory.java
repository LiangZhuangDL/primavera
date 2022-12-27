package main.java.com.study.primaveraframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import main.java.com.study.primaveraframework.beans.BeanException;
import main.java.com.study.primaveraframework.beans.PropertyValue;
import main.java.com.study.primaveraframework.beans.PropertyValues;
import main.java.com.study.primaveraframework.beans.factory.config.AutowireCapableBeanFactory;
import main.java.com.study.primaveraframework.beans.factory.config.BeanDefinition;
import main.java.com.study.primaveraframework.beans.factory.config.BeanPostProcessor;
import main.java.com.study.primaveraframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 实现默认Bean创建的抽象bean工厂超类
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object create(String beanName, BeanDefinition beanDefinition) throws BeanException {
        Object bean = null;
        try{
            bean = beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();


        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeanException("Instantiation of bean failed", e);
        }
        registrySingleton(beanName, bean);
        return bean;
    }

    @Override
    protected Object create(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException{
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition, beanName, args);
        }catch (Exception e){
            throw new BeanException("Instantiation of bean failed", e);
        }
        registrySingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            if(args !=null && constructor.getParameterTypes().length == args.length){
                constructorToUse = constructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy(){
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy){
        this.instantiationStrategy = instantiationStrategy;
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if( value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean, name, value);
            }
        }catch (Exception e){
            throw new BeanException("Error setting property values：" + beanName);
        }
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeanException{
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition, beanName, args);
            applyPropertyValues(beanName, bean, beanDefinition);
            this.initializeBean(beanName, bean, beanDefinition);
        }catch (Exception e){
            throw new BeanException("Instantiation of bean failed", e);
        }
        registrySingleton(beanName, beanDefinition);
        return bean;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){
        Object wrappedBean = this.applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);
        wrappedBean = this.applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition){

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName){
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessorBeforeInitialization(existingBean, beanName);
            if(null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName){
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessorAfterInitialization(existingBean, beanName);
            if(null == current) return result;
            result = current;
        }
        return result;
    }
}
