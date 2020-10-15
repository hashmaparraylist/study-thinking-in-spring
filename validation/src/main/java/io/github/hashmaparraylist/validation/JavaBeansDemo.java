package io.github.hashmaparraylist.validation;

import io.github.hashmaparraylist.ioc.overview.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * JavaBeans 示例
 *
 * @author
 * @date 2020/10/15
 */
public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            System.out.println(propertyDescriptor);
        });
        
        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);
    }
}
