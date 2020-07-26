package io.github.hashmaparraylist.ioc.overview.dependency.lookup;

import io.github.hashmaparraylist.ioc.overview.annotation.Super;
import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Map;

/**
 * Dependency Lookup Demo
 * 通过名称的方式查找
 * @author
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");
        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);
        lookupByType(beanFactory);
        lookupByCollectionType(beanFactory);
        lookupByAnnotationType(beanFactory);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找" + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找" + user);
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找" + user);
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users =listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到所有的 User 集合对象:" + users);
        }
    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("查找到所有标准 @Super 的 User 集合对象:" + users);
        }
    }
}
