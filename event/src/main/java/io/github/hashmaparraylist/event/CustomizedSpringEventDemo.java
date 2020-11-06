package io.github.hashmaparraylist.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 自定义 Spring 事件
 *
 * @author
 * @date 2020/11/4
 */
public class CustomizedSpringEventDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        // 1. 添加自定义ApplicationListener
        context.addApplicationListener(new MySpringEventListener());
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println(event);
            }
        });

        // 2. 启动 Spring 应用上下文
        context.refresh();

        // 3. 发送自定义 Spring 事件
        context.publishEvent(new MySpringEvent("Hello, world!"));
        context.publishEvent(new MySpringEventSub("2020"));

        // 4. 关闭 Spring 应用上下文
        context.close();
    }
}
