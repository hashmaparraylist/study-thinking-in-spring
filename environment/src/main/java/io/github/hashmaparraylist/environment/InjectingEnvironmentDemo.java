package io.github.hashmaparraylist.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 注入 {@link Environment} 示例
 *
 * @author
 * @date 2020/11/17
 * @see Environment
 */
public class InjectingEnvironmentDemo implements EnvironmentAware, ApplicationContextAware {

    private Environment environment;
    private ApplicationContext applicationContext;

    @Autowired
    private Environment environment2;
    @Autowired
    private ApplicationContext applicationContext2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingEnvironmentDemo.class);

        context.refresh();

        InjectingEnvironmentDemo injectingEnvironmentDemo = context.getBean(InjectingEnvironmentDemo.class);
        System.out.println(injectingEnvironmentDemo.environment);
        System.out.println(injectingEnvironmentDemo.environment == injectingEnvironmentDemo.environment2);
        System.out.println(injectingEnvironmentDemo.environment == context.getEnvironment());
        System.out.println(injectingEnvironmentDemo.environment == injectingEnvironmentDemo.applicationContext.getEnvironment());
        System.out.println(injectingEnvironmentDemo.applicationContext == injectingEnvironmentDemo.applicationContext2);
        System.out.println(injectingEnvironmentDemo.applicationContext == context);

        context.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
