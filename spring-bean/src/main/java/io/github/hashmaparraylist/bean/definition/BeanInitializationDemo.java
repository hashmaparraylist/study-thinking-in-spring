package io.github.hashmaparraylist.bean.definition;

import io.github.hashmaparraylist.bean.factory.DefaultUserFactory;
import io.github.hashmaparraylist.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Bean 初始化 示例
 *
 * @author
 */
@Configuration
public class BeanInitializationDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 对象
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        applicationContext.register(BeanInitializationDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 非延迟初始化 在 Spring 应用上下文启动完成后被初始化
        System.out.println("Spring 应用上下文已启动...");
        // 依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        // 关闭应用上下文
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory")
    @Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
