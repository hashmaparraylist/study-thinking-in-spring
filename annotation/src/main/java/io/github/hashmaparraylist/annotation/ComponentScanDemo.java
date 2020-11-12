package io.github.hashmaparraylist.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * {@link Component} 扫描示例
 *
 * @author
 * @date 2020/11/9
 * @see Component
 * @see ComponentScan
 */
@MyComponentScan2(basePackages = "io.github.hashmaparraylist.annotation")
//@ComponentScan(basePackages = "io.github.hashmaparraylist.annotation")
public class ComponentScanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ComponentScanDemo.class);

        context.refresh();

        System.out.println(context.getBean(TestClass.class));

        context.close();
    }
}
