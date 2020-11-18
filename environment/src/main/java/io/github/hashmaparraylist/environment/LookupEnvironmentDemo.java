package io.github.hashmaparraylist.environment;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import static org.springframework.context.ConfigurableApplicationContext.*;

/**
 * 注入 {@link Environment} 示例
 *
 * @author
 * @date 2020/11/17
 * @see Environment
 */
public class LookupEnvironmentDemo implements EnvironmentAware {

    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LookupEnvironmentDemo.class);

        context.refresh();

        LookupEnvironmentDemo lookupEnvironmentDemo = context.getBean(LookupEnvironmentDemo.class);
        System.out.println(lookupEnvironmentDemo.environment);

        // 通过 Environment 的名称
        Environment environment = context.getBean(ENVIRONMENT_BEAN_NAME, Environment.class);
        System.out.println(lookupEnvironmentDemo.environment == environment);

        context.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
