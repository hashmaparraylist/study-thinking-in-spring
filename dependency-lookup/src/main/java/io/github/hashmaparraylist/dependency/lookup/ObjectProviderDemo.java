package io.github.hashmaparraylist.dependency.lookup;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * TODO
 *
 * @author Sebastian Qu
 */
public class ObjectProviderDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注解 Configuration Class 配置类
        applicationContext.register(ObjectProviderDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 依赖查找集合对象
        lookupByObjectProvider(applicationContext);

        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);
        // 关闭容器
        applicationContext.close();
    }

    @Bean
    @Primary
    public String helloWorld() {    // 方法名称是 Bean 名称 = "helloWorld"
        return "Hello world!";
    }

    @Bean
    public String messages() {
        return "Message";
    }

    private static void lookupIfAvailable(ApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("当前 user 对象: " + user);
    }

    private static void lookupByStreamOps(ApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        objectProvider.stream().forEach(System.out::println);
    }

    private static void lookupByObjectProvider(ApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }


}
