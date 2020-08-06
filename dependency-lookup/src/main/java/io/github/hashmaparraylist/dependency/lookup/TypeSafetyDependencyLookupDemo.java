package io.github.hashmaparraylist.dependency.lookup;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全查找的示例
 *
 * @author
 * @date 2020/8/6
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注解 Configuration Class 配置类
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 演示 BeanFactory#getBean 的安全性
        displayBeanFactoryGetBean(applicationContext);
        // 演示 ObjectFactory#getObject 的安全性
        displayObjectFactoryGetObject(applicationContext);
        // 演示 ObjectProvider#getIfAvailable 的安全性
        displayObjectProviderGetIfAvailable(applicationContext);
        // 演示 ListableBeanFactory#getBeansOfType 的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        // 演示 ObjectProvider#stream 的安全性
        displayObjectProviderStream(applicationContext);

        // 关闭容器
        applicationContext.close();
    }

    private static void displayObjectProviderStream(ApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStream", () -> objectProvider.forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactory", () -> beanFactory.getBeansOfType(User.class));
    }

    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void displayObjectFactoryGetObject(ApplicationContext applicationContext) {
        // ObjectProvide is ObjectFactory
        ObjectFactory<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayBeanFactoryGetBean", () -> userObjectFactory.getObject());
    }

    private static void displayObjectProviderGetIfAvailable(ApplicationContext applicationContext) {
        ObjectProvider<User> userObjectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderGetIfAvailable", () -> userObjectFactory.getIfAvailable());
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("====================================================");
        System.err.println("Source from : " + source);
        try {
            runnable.run();
        } catch (BeansException bex) {
            bex.printStackTrace();
        }
    }
}
