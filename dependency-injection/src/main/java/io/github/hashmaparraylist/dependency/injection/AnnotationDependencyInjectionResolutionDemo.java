package io.github.hashmaparraylist.dependency.injection;

import io.github.hashmaparraylist.dependency.injection.annotation.InjectUser;
import io.github.hashmaparraylist.dependency.injection.annotation.MyAutowired;
import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

import static java.util.Arrays.asList;
import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 * 注解驱动的依赖注入处理过程
 *
 * @author
 * @date 2020/8/13
 */
public class AnnotationDependencyInjectionResolutionDemo {

    @Autowired
    @Lazy
    private User lazyUser;

    @Autowired
    private User user; // DependencyDescriptor => 实时注入 + 通过类型 (User.class) + 字段名称 (user)

    @Autowired
    private Map<String, User> users;

    @MyAutowired
    private Optional<User> userOptional; // superUser

    @Inject
    private User injectUser;

    @InjectUser
    private User myInjetUser;

//    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        // @Autowired + @InjectUser
//        Set<Class<? extends Annotation>> autowiredAnnotationType = new LinkedHashSet<>(asList(Autowired.class, Inject.class, InjectUser.class));
//        beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationType);
//        return beanPostProcessor;
//    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectUser.class);
        return beanPostProcessor;
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注解 Configuration Class 配置类
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源并解析生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 QualifierAnnotationDependencyInjectionDemo Bean
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        System.out.println("demo.user = " + demo.user);
        System.out.println("demo.users = " + demo.users);
        System.out.println("demo.userOptional = " + demo.userOptional);
        System.out.println("demo.lazyUser = " + demo.lazyUser);
        System.out.println("demo.injectUser = " + demo.injectUser);
        System.out.println("demo.myInjectUser = " + demo.myInjetUser);

        // 关闭容器
        applicationContext.close();
    }
}
