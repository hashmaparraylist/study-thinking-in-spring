package io.github.hashmaparraylist.validation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Spring Bean Validation 整合示例
 *
 * @author
 * @date 2020/10/12
 *
 * @see Validator
 * @see LocalValidatorFactoryBean
 *
 */
public class SpringBeanValidationDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");

        // 通过 ApplicationContext 获取 LocalValidatorFactoryBean
//        Validator validator = applicationContext.getBean(Validator.class);
//        System.out.println(validator instanceof LocalValidatorFactoryBean);

        UserProcessor userProcessor = applicationContext.getBean(UserProcessor.class);
        userProcessor.processUser(new User());

        applicationContext.close();
    }

    @Component
    @Validated
    static class UserProcessor {
        public void processUser(@Valid User user) {
            System.out.println(user);
        }
    }

    static class User {
        @NotNull
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
