package io.github.hashmaparraylist.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link ApplicationListener} 示例
 *
 * @author
 * @date 2020/11/3
 * @see ApplicationListener
 * @see EventListener
 */
@EnableAsync
public class ApplicationListenerDemo {

    public static void main(String[] args) {
//        GenericApplicationContext context = new GenericApplicationContext();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 将引导类 ApplicationListenerDemo 作为 Configuration Class
        context.register(ApplicationListenerDemo.class);

        // 方法一 基于 Spring 接口 : 向 Spring 应用上下文注册事件
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                println("接收到 Spring 事件: " + event);
            }
        });

        // 方法二 基于 Spring 注解: @EventListener

        // 启动 Spring 应用上下文
        context.refresh();
        // 启动 Spring 应用上下文
        context.start();
        // 关闭 Spring 应用上下文
        context.close();
    }

    @EventListener
    @Order(1)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        println("@EventListener (onApplicationEvent) - 接收到 Spring ContextRefreshedEvent 事件");
    }

    @EventListener
    @Order(0)
    public void onApplicationEvent1(ContextRefreshedEvent event) {
        println("@EventListener (onApplicationEvent1) - 接收到 Spring ContextRefreshedEvent 事件");
    }

    @EventListener
    @Async
    public void onApplicationEventAsync(ContextRefreshedEvent event) {
        println("@EventListener (异步) - 接收到 Spring ContextRefreshedEvent 事件");
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        println("@EventListener - 接收到 Spring ContextStartedEvent 事件");
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        println("@EventListener - 接收到 Spring ContextRefreshedEvent 事件");
    }

    public static void println(Object printable) {
        System.out.printf("[线程 %s] : %s\n", Thread.currentThread().getName(), printable);
    }
}
