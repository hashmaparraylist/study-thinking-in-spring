package io.github.hashmaparraylist.bean.definition;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 注解 BeanDefinition 示例
 *
 * @author
 */
@Import(AnnotationBeanDefinitionDemo.Config.class) // 通过 @Import 导入
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注解 Configuration Class 配置类
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        // 通过 BeanDefinition 注册 API 实现
        // 命名方式
        registerUserBeanDefinition(applicationContext, "mercy");
        // 非命名方式
        registerUserBeanDefinition(applicationContext);

        applicationContext.refresh();

        System.out.println("Config 类型的所有 Beans" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有 Beans" + applicationContext.getBeansOfType(User.class));

        // 关闭容器
        applicationContext.close();
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String name) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L).addPropertyValue("name", "Foobar");

        // 判断 beanName 是否存在
        // 注册 BeanDefinition
        if (StringUtils.hasText(name)) {
            // 命名方式
            registry.registerBeanDefinition(name, beanDefinitionBuilder.getBeanDefinition());
        }
        else {
            // 非命名方式
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }

    }

    /**
     *
     * @param registry
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    @Component // 通过 @Component 方式
    public static class Config {
        /**
         * 通过 Java 注解方式,定义一个 Bean
         * @return
         */
        @Bean(name = {"user", "testUser"})   // 1. 通过 @Bean 方式定义
        public User user() {
            User user = new User();
            user.setId(2L);
            user.setName("Tommy");
            return user;
        }
    }

}
