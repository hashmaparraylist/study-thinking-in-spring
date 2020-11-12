package io.github.hashmaparraylist.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解属性覆盖示例
 *
 * @author
 * @date 2020/11/12
 */
@MyComponentScan2(packages = "io.github.hashmaparraylist.annotation")
// @MyComponentScan2.scanBasePackages <- @MyComponentScan.scanBasePackages 隐性覆盖
public class AttributeOverridesDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ComponentScanDemo.class);

        context.refresh();

        System.out.println(context.getBean(TestClass.class));

        context.close();
    }
}
