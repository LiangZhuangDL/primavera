package main.java.com.study.primaveraframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import main.java.com.study.primaveraframework.beans.BeanException;
import main.java.com.study.primaveraframework.beans.factory.PropertyValue;
import main.java.com.study.primaveraframework.beans.factory.config.BeanDefinition;
import main.java.com.study.primaveraframework.beans.factory.config.BeanReference;
import main.java.com.study.primaveraframework.beans.factory.support.AbstractBeanDefinitionReader;
import main.java.com.study.primaveraframework.beans.factory.support.BeanDefinitionRegistry;
import main.java.com.study.primaveraframework.core.io.Resource;
import main.java.com.study.primaveraframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry, ResourceLoader resourceLoader) {
        super(beanDefinitionRegistry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanException {
        try{
            try(InputStream inputStream = resource.getInputStream()){
                doLoadBeanDefinitions(inputStream);
            }
        }catch (IOException | ClassNotFoundException e){
            throw new BeanException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeanException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeanException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException{
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            if(!(childNodes.item(i) instanceof Element)) continue;
            if(!("bean".equals(childNodes.item(i).getNodeName()))) continue;
            Element element = (Element) childNodes.item(i);
            String id = element.getAttribute("id");
            String name = element.getAttribute("name");
            String className = element.getAttribute("class");
            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if(StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            for (int j = 0; j < element.getChildNodes().getLength(); j++) {
                if(!(element.getChildNodes().item(j) instanceof Element)) continue;
                if(!"property".equals(element.getChildNodes().item(j).getNodeName())) continue;
                Element property = (Element) element.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) :attrValue;
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if(getRegistry().containsBeanDefinition(beanName)){
                throw new BeanException("Duplicate beanName [" + beanName + "] is not allowed");
            }
            getRegistry().registryBeanDefinition(beanName, beanDefinition);
        }
    }
}
