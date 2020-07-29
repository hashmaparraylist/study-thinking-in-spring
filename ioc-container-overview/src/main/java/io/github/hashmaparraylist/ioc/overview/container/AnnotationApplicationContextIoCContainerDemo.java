package io.github.hashmaparraylist.ioc.overview.container;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 注解能力 ApplicationContext 作为 IoC 容器为例
 *
 * @author
 */
@Configuration
public class AnnotationApplicationContextIoCContainerDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类 AnnotationApplicationContextIoCContainerDemo 作为配置类 (Configuration Class)
        applicationContext.register(AnnotationApplicationContextIoCContainerDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        lookupByCollectionType(applicationContext);
        // 关闭容器
        applicationContext.close();
    }

    /**
     * 通过 Java 注解方式,定义一个 Bean
     * @return
     */
    @Bean
    public User user() {
        User user = new User();
        user.setId(2L);
        user.setName("Tommy");
        return user;
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users =listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到所有的 User 集合对象:" + users);
        }
    }
}
