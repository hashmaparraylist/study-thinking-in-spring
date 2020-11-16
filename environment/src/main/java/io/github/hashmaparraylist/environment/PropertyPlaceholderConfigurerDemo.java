package io.github.hashmaparraylist.environment;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@link PropertyPlaceholderConfigurer} 处理属性占位符示例
 *
 * @author
 * @date 2020/11/16
 * @see PropertyPlaceholderConfigurer
 */
public class PropertyPlaceholderConfigurerDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:META-INF/placeholders-resolver.xml");

        User user = context.getBean("user", User.class);

        System.out.println(user);

        context.close();
    }
}
