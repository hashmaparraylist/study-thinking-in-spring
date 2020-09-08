package io.github.hashmaparraylist.ioc.bean.lifecycle;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * TODO
 *
 * @author
 * @date 2020/9/8
 */
public class BeanLifecycleDemo {
    public static void main(String[] args) throws Throwable {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 添加 DestructionAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        // 方法一:添加 BeanPostProcessor 的实现 MyInstantiationAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 方法二:MyInstantiationAwareBeanPostProcessor 作为 Bean 注册
        // 添加 CommonAnnotationBeanPostProcessor 解决 @PostConstruct 未被调用的问题
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
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

        // 执行 Bean 销毁
        beanFactory.destroyBean("userHolder", userHolder);
        // Bean 销毁并不意味着 Bean 被垃圾回收
        System.out.println(userHolder);

        // 彻底销毁 BeanFactory 的单例对象
        beanFactory.destroySingletons();
        // 强制 GC
        System.gc();
        // 等待一段时间
        Thread.sleep(1000L);
        // 强制 GC
        System.gc();
    }
}
