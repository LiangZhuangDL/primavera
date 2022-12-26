package main.java.com.study.primaveraframework.beans;

public class BeanException extends RuntimeException{

    public BeanException(String message){
        super(message);
    }

    public BeanException(String message, Throwable cause) {
        super(message, cause);
    }
}
