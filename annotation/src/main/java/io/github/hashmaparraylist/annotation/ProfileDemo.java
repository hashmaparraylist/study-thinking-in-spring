package io.github.hashmaparraylist.annotation;

import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

/**
 * {@link Profile} 示例
 *
 * @author
 * @date 2020/11/16
 *
 * @see Profile
 * @see Environment#getActiveProfiles()
 */
@Configuration
public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ProfileDemo.class);

        // 获取 Environment 对象
        ConfigurableEnvironment environment = context.getEnvironment();
        // 默认 profiles = [ "odd" ]
        environment.setDefaultProfiles("odd");
        // 增加活跃 profiles
//        environment.addActiveProfile("even");

        // --Dspring.profiles.active=even

        context.refresh();

        Integer number = context.getBean("number", Integer.class);

        System.out.println(number);

        context.close();
    }

    @Bean(name = "number")
    @Profile("odd")
    public Integer odd() {
        return 1;
    }

    @Bean(name = "number")
//    @Profile("even")
    @Conditional(EvenProfileCondition.class)
    public Integer even() {
        return 2;
    }
}
