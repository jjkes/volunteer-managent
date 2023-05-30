package com.zj.file.util;

import com.zj.entities.file.annotation.XmlValue;
import org.dom4j.Element;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 赵健
 * @version 1.0
 * @description: TODO
 * @date 2023/3/10 13:11
 */

public class XMLUtil {

    public static Object parseXML(Element root,Class<?> clazz) throws InstantiationException, IllegalAccessException {
        Object instance = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 最普通的节点，只需要传入一个path,将xml内容注入
            if (field.getAnnotation(XmlValue.class) != null) {
                try {
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(),clazz);
                    Method writeMethod = pd.getWriteMethod();
                    XmlValue annotation = field.getAnnotation(XmlValue.class);
                    String path = annotation.path();
                    String xmlValue = getXmlValue(root, path);
                    writeMethod.invoke(instance,xmlValue);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
        return instance;
    }

    static String getXmlValue(Element root,String path){
        Element element = null;
        if(root != null){
            for (String childrenPath : path.split("/")) {
                element = root.element(childrenPath);
            }
            if(element != null){
                return element.getStringValue();
            }
        }
        return null;
    }


}
