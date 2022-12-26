package main.java.com.study.primaveraframework.utils;

public class ClassUtils {

    public static ClassLoader getDefaultClassLoader(){
        ClassLoader classLoader = null;
        try{
            classLoader = Thread.currentThread().getContextClassLoader();
        }catch (Throwable e){

        }
        if(classLoader == null){
            classLoader = ClassUtils.class.getClassLoader();
        }
        return classLoader;
    }
}
