package io.github.hashmaparraylist.configuration.metadata;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import io.github.hashmaparraylist.ioc.overview.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * 基于 Java 注解的 YAML 外部化配置
 *
 * @author
 * @date 2020/9/16
 */
@PropertySource(
        name = "yaml",
        value = "classpath:/META-INF/user.yaml",
        factory=YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

    @Bean
    public User configuredUser(@Value("${user.id}") Long id, @Value("${user.name}") String name, @Value("${user.city}") City city) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        applicationContext.register(AnnotatedYamlPropertySourceDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 获取 User 对象
        User user = applicationContext.getBean(User.class);
        System.out.println(user);

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }
}
