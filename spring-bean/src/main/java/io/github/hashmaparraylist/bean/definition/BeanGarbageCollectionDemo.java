package io.github.hashmaparraylist.bean.definition;

import io.github.hashmaparraylist.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean GC 示例
 *
 * @author
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 对象
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        applicationContext.register(BeanInitializationDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 非延迟初始化 在 Spring 应用上下文启动完成后被初始化
        System.out.println("Spring 应用上下文已启动...");
        // 关闭应用上下文
        System.out.println("Spring 应用上下文准备关闭...");
        applicationContext.close();
        System.out.println("Spring 应用上下文已关闭...");
        // 强制 GC
        System.gc();
    }
}
