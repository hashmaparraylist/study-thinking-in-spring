package io.github.hashmaparraylist.application.context.lifecycle;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.Lifecycle;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 自定义 {@link Lifecycle} Bean 示例
 *
 * @author
 * @date 2020/11/23
 * @see Lifecycle
 */
public class LifecycleDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        // 注册 MyLifecycle 成为一个 Spring Bean
        context.registerBeanDefinition("myLifecycle",
                BeanDefinitionBuilder.rootBeanDefinition(MyLifecycle.class).getBeanDefinition());

        // 刷新 Spring 应用上下文
        context.refresh();

        // 启动 Spring 应用上下文
        context.start();

        // 停止 Spring 应用上下文
        context.stop();

        // 关闭 Spring 应用上下文
        context.close();
    }
}
