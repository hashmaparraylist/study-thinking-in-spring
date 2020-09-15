package io.github.hashmaparraylist.configuration.metadata;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import javafx.beans.property.MapProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 外部化配置
 *
 * @author
 * @date 2020/9/15
 */
@PropertySource("classpath:/META-INF/users-config.properties")
public class PropertySourceDemo {
    @Bean
    public User configuredUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);

        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 扩展 Environment 中的 PropertySource
        // 添加 PropertySource 必须在 refresh 之前完成
        Map<String, Object> source = new HashMap<>();
        source.put("user.name", "hashmaparraylist");
        org.springframework.core.env.PropertySource propertySource = new MapPropertySource("first-property-source", source);
        context.getEnvironment().getPropertySources().addFirst(propertySource);

        // 注册当前类为 Configuration Class
        context.register(PropertySourceDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();

        Map<String, User> usersMap = context.getBeansOfType(User.class);
        usersMap.forEach((String key, User user) -> {
            System.out.printf("User Bean name : %s , content : %s \n", key, user);
        });

        System.out.println(context.getEnvironment().getPropertySources());

        // 关闭 Spring 应用上下文
        context.close();
    }
}
