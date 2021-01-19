package io.github.hashmaparraylist.aop.features;

import io.github.hashmaparraylist.aop.features.aspect.AspectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Pointcut 示例
 *
 * @author
 * @date 2021/1/15
 */
@Configuration          // Configuration class
@EnableAspectJAutoProxy // 激活 AspectJ 注解自动代理
public class AspectJAnnotationPointcutDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AspectJAnnotationPointcutDemo.class, AspectConfiguration.class);
        context.refresh();

        AspectJAnnotationPointcutDemo aspectJAnnotationDemo = context.getBean(AspectJAnnotationPointcutDemo.class);

        aspectJAnnotationDemo.execute();

        context.close();
    }

    public void execute() {

    }
}
