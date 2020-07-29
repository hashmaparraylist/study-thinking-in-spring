package io.github.hashmaparraylist.ioc.overview.dependency.injection;

import io.github.hashmaparraylist.ioc.overview.annotation.Super;
import io.github.hashmaparraylist.ioc.overview.domain.User;
import io.github.hashmaparraylist.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Collection;
import java.util.Map;

/**
 * Dependency Lookup Demo
 * 通过名称的方式查找
 * @author
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
//        ApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        // 依赖来源一: 自定义 Bean
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
//        System.out.println(userRepository.getUsers());

        whoIsIocContainer(userRepository, beanFactory);

        // 依赖来源二: 依赖注入 (内建依赖)
        System.out.println(userRepository.getBeanFactory());

        ObjectFactory userFactory = userRepository.getUserObjectFactory();
        System.out.println(userFactory.getObject());
        System.out.println(userFactory.getObject() == beanFactory);

        // 依赖查找 (错误)
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        // 依赖来源三: 容器内建 Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的对象" + environment);
    }

    private static void whoIsIocContainer(UserRepository userRepository, BeanFactory beanFactory) {
        System.out.println(userRepository.getBeanFactory() == ((ClassPathXmlApplicationContext) beanFactory).getBeanFactory());
    }
}

