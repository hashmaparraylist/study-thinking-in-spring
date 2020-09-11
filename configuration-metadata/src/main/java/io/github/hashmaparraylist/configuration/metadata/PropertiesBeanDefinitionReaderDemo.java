package io.github.hashmaparraylist.configuration.metadata;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * {@link org.springframework.beans.factory.support.PropertiesBeanDefinitionReader} 的示例
 *
 * @author
 * @date 2020/9/10
 */
public class PropertiesBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        // 创建 IoC 底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建面向 Properties 的 BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        // Properties 资源加载默认使用 ISO-8859-1 编码
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/META-INF/users-config.properties");
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanDefinitionCounter = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.printf("已有 %d 个 BeanDefinition 被加载。%n", beanDefinitionCounter);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
