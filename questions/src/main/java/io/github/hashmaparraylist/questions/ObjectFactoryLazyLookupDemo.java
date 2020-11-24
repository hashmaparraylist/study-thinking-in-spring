package io.github.hashmaparraylist.questions;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * {@link ObjectFactory} 延迟依赖查找示例
 *
 * @author
 * @date 2020/11/24
 */
public class ObjectFactoryLazyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ObjectFactoryLazyLookupDemo.class);
        context.refresh();

        ObjectFactoryLazyLookupDemo objectFactoryLazyLookupDemo = context.getBean(ObjectFactoryLazyLookupDemo.class);

        // userObjectFactory == userObjectProvider
        ObjectFactory<User> userObjectFactory = objectFactoryLazyLookupDemo.userObjectFactory;
        ObjectProvider<User> userObjectProvider = objectFactoryLazyLookupDemo.userObjectProvider;
        System.out.println("userObjectFactory == userObjectProvider : " +
                (userObjectFactory == userObjectProvider));

        System.out.println("userObjectFactory.getClass() == userObjectProvider.getClass() : " +
                (userObjectFactory.getClass() == userObjectProvider.getClass()));

        System.out.println("user=" + userObjectFactory.getObject());
        System.out.println("user=" + userObjectProvider.getObject());
        System.out.println("user=" + context.getBean(User.class));


        context.close();
    }

    @Autowired
    private ObjectFactory<User> userObjectFactory;

    @Autowired
    private  ObjectProvider<User> userObjectProvider;

    @Bean
    @Lazy
    public static User user() {
        User user = new User();
        user.setId(1L);
        user.setName("ObjectFactory延迟依赖查找");
        return user;
    }
}
