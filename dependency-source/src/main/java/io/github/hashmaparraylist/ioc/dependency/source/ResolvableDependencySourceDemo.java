package io.github.hashmaparraylist.ioc.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * ResolvableDependency 示例
 *
 * @author
 * @date 2020/8/20
 */
public class ResolvableDependencySourceDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注解 Configuration Class 配置类
        applicationContext.register(ResolvableDependencySourceDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册 ResolvableDependency
            beanFactory.registerResolvableDependency(String.class, "Hello, world!");
        });

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 关闭容器
        applicationContext.close();
    }
}
