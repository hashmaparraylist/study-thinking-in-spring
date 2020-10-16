package io.github.hashmaparraylist.conversion;

import io.github.hashmaparraylist.ioc.overview.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyEditor;

/**
 * Spring 自定义 {@link PropertyEditor} 示例
 *
 * @author
 * @date 2020/10/16
 *
 * @see PropertyEditor
 */
public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/property-editors-context.xml");

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        applicationContext.close();
    }
}
