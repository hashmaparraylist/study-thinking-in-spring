package io.github.hashmaparraylist.dependency.injection;

import io.github.hashmaparraylist.dependency.injection.annotation.UserGroup;
import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * {@link org.springframework.beans.factory.annotation.Qualifier} 注解的依赖注入
 *
 * @author
 * @see org.springframework.beans.factory.annotation.Qualifier
 * @date 2020/8/12
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User namedUser;

    // 应用上下文存在 4个 User 类型的Bean
    // user
    // superUser
    // user1 --> @Qualifier
    // user2 --> @Qualifier

    @Autowired
    private Collection<User> allUsers;          // user + superUser

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;    // user1 + user2 + user3 + user4

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers; // user3 + user4

    @Bean
    @Qualifier      // 进行逻辑分组
    public User user1() {
        return createUser(7L);
    }

    @Bean
    @Qualifier      // 进行逻辑分组
    public User user2() {
        return createUser(8L);
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(10L);
    }

    private static User createUser(long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注解 Configuration Class 配置类
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源并解析生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.namedUser = " + demo.namedUser);
        System.out.println("demo.allUsers = " + demo.allUsers);
        System.out.println("demo.qualifierUsers = " + demo.qualifierUsers);
        System.out.println("demo.groupedUsers = " + demo.groupedUsers);

        // 关闭容器
        applicationContext.close();
    }
}
