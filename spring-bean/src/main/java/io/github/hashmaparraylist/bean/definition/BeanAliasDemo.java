package io.github.hashmaparraylist.bean.definition;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author
 */
public class BeanAliasDemo {
    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definition-context.xml");
        User aliasUser = (User) beanFactory.getBean("alias-user");
        User user = (User) beanFactory.getBean("user");
        System.out.println("alias-user == user : " + (user == aliasUser));
    }

}
