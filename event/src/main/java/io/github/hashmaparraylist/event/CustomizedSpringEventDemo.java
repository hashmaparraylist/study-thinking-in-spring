package io.github.hashmaparraylist.event;

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

        // 2. 启动 Spring 应用上下文
        context.refresh();

        // 3. 发送自定义 Spring 事件
        context.publishEvent(new MySpringEvent("Hello, world!"));

        // 4. 关闭 Spring 应用上下文
        context.close();
    }
}
