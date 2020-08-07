package io.github.hashmaparraylist.dependency.lookup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@link org.springframework.beans.factory.BeanDefinitionStoreException} 示例
 *
 * @author
 * @date 2020/8/7
 */
public class BeanDefinitionStoreExceptionDemo {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-store-exception.xml");
    }
}
