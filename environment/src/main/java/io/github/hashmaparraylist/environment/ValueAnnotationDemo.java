package io.github.hashmaparraylist.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link Value} 示例
 *
 * @author
 * @date 2020/11/18
 * @see Value
 */
public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String name;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ValueAnnotationDemo.class);
        context.refresh();

        ValueAnnotationDemo valueAnnotationDemo = context.getBean(ValueAnnotationDemo.class);

        System.out.println(valueAnnotationDemo.name);

        context.close();
    }
}
