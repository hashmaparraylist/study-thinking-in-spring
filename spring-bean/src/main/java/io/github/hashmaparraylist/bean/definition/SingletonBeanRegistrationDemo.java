package io.github.hashmaparraylist.bean.definition;

import io.github.hashmaparraylist.bean.factory.DefaultUserFactory;
import io.github.hashmaparraylist.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体Bean注册示例
 *
 * @author
 */
public class SingletonBeanRegistrationDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 对象
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 创建一个外部对象
        UserFactory userFactory = new DefaultUserFactory();
        ConfigurableBeanFactory beanFactory = applicationContext.getBeanFactory();
        // 注册外部单体示例
        beanFactory.registerSingleton("userFactory", userFactory);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 通过依赖查找获取 UserFactory
        UserFactory userFactoryByLookup = beanFactory.getBean("userFactory", UserFactory.class);
        System.out.println("userFactory == userFactoryByLookup" + (userFactory == userFactoryByLookup));

        // 关闭应用上下文
        applicationContext.close();

    }
}
