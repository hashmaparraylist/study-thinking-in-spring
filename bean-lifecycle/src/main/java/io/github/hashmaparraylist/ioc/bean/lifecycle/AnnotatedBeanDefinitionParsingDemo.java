package io.github.hashmaparraylist.ioc.bean.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * 注解 BeanDefinition 解析示例
 *
 * @author
 * @date 2020/8/31
 */
public class AnnotatedBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 基于 Java 注解的 BeanDefinitionReader 的实现
        AnnotatedBeanDefinitionReader beanDefinitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        int beanDefinitionCounterBefore = beanFactory.getBeanDefinitionCount();
        // 注册当前类(非 @Component class)
        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        int beanDefinitionCounterAfter = beanFactory.getBeanDefinitionCount();
        int beanDefinitionCounter = beanDefinitionCounterAfter - beanDefinitionCounterBefore;
        System.out.printf("已加载 BeanDefinition 数量: %d%n", beanDefinitionCounter);
        // 普通的 Class 作为 Component 注册到 Spring IoC 容器后，通常 Bean 名称为 annotatedBeanDefinitionParsingDemo
        // Bean 名称生成来自于 BeanNameGenerator, 注解实现 AnnotationBeanNameGenerator
        AnnotatedBeanDefinitionParsingDemo demo = beanFactory.getBean("annotatedBeanDefinitionParsingDemo",
                AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(demo);
    }

}
