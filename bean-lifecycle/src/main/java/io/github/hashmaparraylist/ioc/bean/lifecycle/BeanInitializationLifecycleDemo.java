package io.github.hashmaparraylist.ioc.bean.lifecycle;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 初始化化生命周期示例
 *
 * @author
 * @date 2020/9/2
 */
public class BeanInitializationLifecycleDemo {
    public static void main(String[] args) {
        executeBeanFactory();
    }

    public static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct 未被调用的问题
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        // 方法一:添加 BeanPostProcessor 的实现 MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 方法二:MyInstantiationAwareBeanPostProcessor 作为 Bean 注册
        // 基于 XML 资源的 BeanDefinitionReader 的实现
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] locations = {"classpath:META-INF/dependency-lookup-context.xml", "classpath:META-INF/bean-constructor-dependency-injection.xml"};
        int beanDefinitionCount = beanDefinitionReader.loadBeanDefinitions(locations);


        System.out.printf("已加载 BeanDefinition 数量: %d%n", beanDefinitionCount);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);

        // 构造器注入是按照类型去选择参数， resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        
        // 需要显示回调
        // SmartInitializingSingleton 通常在ApplicationContext中使用。
        beanFactory.preInstantiateSingletons();

        System.out.println(userHolder);

    }
}

