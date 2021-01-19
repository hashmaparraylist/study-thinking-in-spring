package io.github.hashmaparraylist.aop.features;

import io.github.hashmaparraylist.aop.features.aspect.AspectConfiguration;
import io.github.hashmaparraylist.aop.overview.EchoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 基于 XML 配置Pointcut 示例
 *
 * @author
 * @date 2021/1/15
 */
@Configuration          // Configuration class
@EnableAspectJAutoProxy // 激活 AspectJ 注解自动代理
public class AspectJSchemaBasedPointcutDemo {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/META-INF/spring-aop-context.xml");

        context.refresh();

        EchoService echoService = context.getBean("echoService", EchoService.class);

        System.out.println(echoService.echo("Hello, world."));

        context.close();
    }

}
