package io.github.hashmaparraylist.configuration.metadata;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * 基于 Java 注解 Spring IoC 容器元信息配置 示例
 *
 * @author
 * @date 2020/9/14
 */
// 将当前类作为 Configuration Class
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/users-config.properties")      // Java 8 @Repeatable 支持
@PropertySource("classpath:/META-INF/users-config.properties")
//@PropertySources(@PropertySource(...))
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {

    @Bean
    public User configuredUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        applicationContext.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        Map<String, User> usersMap = applicationContext.getBeansOfType(User.class);
        usersMap.forEach((String key, User user) -> {
            System.out.printf("User Bean name : %s , content : %s \n", key, user);
        });

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }
}
