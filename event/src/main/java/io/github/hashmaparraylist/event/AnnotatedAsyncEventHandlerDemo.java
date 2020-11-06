package io.github.hashmaparraylist.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * 基于注解的异步事件处理示例
 *
 * @author
 * @date 2020/11/5
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 1. 注册当前类为 Configuration Class
        context.register(AnnotatedAsyncEventHandlerDemo.class);

        // 2. 启动 Spring 应用上下文
        context.refresh(); // 初始化 ApplicationEventMultiCaster

        // 3. 发送自定义 Spring 事件
        context.publishEvent(new MySpringEvent("Hello, world!"));

        // 4. 关闭 Spring 应用上下文
        context.close();
    }

    @Async      // 同步 -> 异步
    @EventListener
    public void onEvent(MySpringEvent event) {
        System.out.printf("[线程 :     %s] onEvent 方法监听事件 : %s\n", Thread.currentThread().getName(), event);
    }

    @Bean
    public Executor taskExecutor() {
        return newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-annotated-event-thread-pool"));
    }

}
