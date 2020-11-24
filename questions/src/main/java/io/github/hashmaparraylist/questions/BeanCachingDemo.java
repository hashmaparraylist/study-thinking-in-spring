package io.github.hashmaparraylist.questions;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * Bean 缓存示例
 *
 * @author
 * @date 2020/11/24
 */
public class BeanCachingDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanCachingDemo.class);
        context.refresh();

        BeanCachingDemo beanCachingDemo = context.getBean(BeanCachingDemo.class);

        for (int i = 0; i < 9; i++) {
            // Singleton Scope Bean 是存在缓存
            System.out.println(beanCachingDemo == context.getBean(BeanCachingDemo.class));
        }

        User user = context.getBean(User.class);
        for (int i = 0; i < 9; i++) {
            System.out.println(user == context.getBean(User.class));
        }

        context.close();
    }

    @Bean
    @Scope("prototype")
    public static User user() {
        User user = new User();
        user.setId(1L);
        user.setName("ObjectFactory延迟依赖查找");
        return user;
    }
}
