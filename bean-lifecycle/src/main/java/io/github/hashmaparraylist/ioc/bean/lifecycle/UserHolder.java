package io.github.hashmaparraylist.ioc.bean.lifecycle;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * TODO
 *
 * @author
 * @date 2020/9/2
 */
public class UserHolder implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, EnvironmentAware,
        InitializingBean, SmartInitializingSingleton {
    private final User user;
    private Integer number;
    private String description;
    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private String beanName;
    private Environment environment;

    /**
     * 依赖于注解驱动
     * 当前场景: BeanFactory
     */
    @PostConstruct
    public void initPostConstruct() {
        this.description = "The use holder v4";
        System.out.printf("initPostConstruct() = %s%n", this.description);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.description = "The user holder v5";
        System.out.printf("afterPropertiesSet() = %s%n", this.description);
    }

    public void init() {
        this.description = "The user holder v6";
        System.out.printf("init() = %s%n", this.description);
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.description = "The user holder V8";
        System.out.printf("afterSingletonsInstantiated() = %s%n", this.description);
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
