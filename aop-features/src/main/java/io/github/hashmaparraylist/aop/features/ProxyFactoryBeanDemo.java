package io.github.hashmaparraylist.aop.features;

import io.github.hashmaparraylist.aop.overview.EchoService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 *
 * @author
 * @date 2021/1/15
 */
@Aspect                 // 声明为 Aspect 切面
@Configuration          // Configuration class
public class ProxyFactoryBeanDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");

        EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);
        System.out.println(echoService.echo("Hello, world!"));

        context.close();
    }
}
