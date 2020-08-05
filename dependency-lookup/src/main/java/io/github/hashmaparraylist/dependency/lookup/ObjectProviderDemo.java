package io.github.hashmaparraylist.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

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
        // 关闭容器
        applicationContext.close();
    }

    @Bean
    public String helloWorld() {    // 方法名称是 Bean 名称 = "helloWorld"
        return "Hello world!";
    }

    private static void lookupByObjectProvider(ApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(objectProvider.getObject());
    }


}
