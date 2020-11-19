package io.github.hashmaparraylist.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link Environment} 配置属性源变更示例
 *
 * @author
 * @date 2020/11/19
 * @see Environment
 */
public class EnvironmentPropertySourceChangeDemo {

    @Value("${user.name}") // 不具备动态更新能力
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnvironmentPropertySourceChangeDemo.class);

        // 在 Spring 应用上下文启动前，调整 Environment 中的 PropertySource
        ConfigurableEnvironment environment = context.getEnvironment();
        // 获取 MutablePropertySource 对象
        MutablePropertySources propertySources = environment.getPropertySources();
        // 动态插入 PropertySource 到 PropertySources 中去。
        Map<String, Object> source = new HashMap<>();
        source.put("user.name", "测试员");
        MapPropertySource propertySource = new MapPropertySource("first-property-source", source);
        propertySources.addFirst(propertySource);

        context.refresh();

        source.put("user.name", "测试员007");

        EnvironmentPropertySourceChangeDemo environmentPropertySourceChangeDemo =
                context.getBean(EnvironmentPropertySourceChangeDemo.class);

        System.out.println(environmentPropertySourceChangeDemo.userName);

        for (PropertySource ps : propertySources) {
            System.out.printf("PropertySource(name=%s) 'user.name' 属性 %s\n", ps.getName(), ps.getProperty("user.name"));
        }

        context.close();
    }

}
