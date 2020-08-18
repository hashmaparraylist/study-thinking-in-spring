package io.github.hashmaparraylist.ioc.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖查找来源
 *
 * @author
 * @date 2020/8/18
 */
public class DependencySourceDemo {

    // 注入在 postProcessProperties 方法执行，早于 setter 注入, @PostConstruct
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() {
        System.out.println("beanFactory == applicationContext : " + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory() : " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext : " + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext : " + (applicationEventPublisher == applicationContext));
    }

    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> clazz) {
        try {
            beanFactory.getBean(clazz);
        } catch (NoSuchBeanDefinitionException ex) {
            System.out.println("当前类型 " + clazz.getTypeName() + " 没有在BeanFactory内找到。");
        }
        return null;
    }


    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注解 Configuration Class 配置类
        applicationContext.register(DependencySourceDemo.class);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        DependencySourceDemo demo = applicationContext.getBean(DependencySourceDemo.class);

        // 关闭容器
        applicationContext.close();
    }
}
