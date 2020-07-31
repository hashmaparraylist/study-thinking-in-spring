package io.github.hashmaparraylist.bean.definition;

import io.github.hashmaparraylist.bean.factory.DefaultUserFactory;
import io.github.hashmaparraylist.bean.factory.UserFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

        // 依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

        // 关闭应用上下文
        applicationContext.close();
    }

    @Bean(initMethod = "initUserFactory")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
