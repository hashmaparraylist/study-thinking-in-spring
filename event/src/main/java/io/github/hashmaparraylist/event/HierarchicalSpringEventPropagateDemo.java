package io.github.hashmaparraylist.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 层次性 Spring 事件传播示例
 *
 * @author
 * @date 2020/11/4
 */
public class HierarchicalSpringEventPropagateDemo {

    public static void main(String[] args) {
        // 1. 创建 Parent Spring 应用上下文
        AnnotationConfigApplicationContext parent = new AnnotationConfigApplicationContext();
        parent.setId("parent-context");
        // 注册 MyListener 到 Parent
        parent.register(MyListener.class);
        // 2. 创建 current Spring 应用上下文
        AnnotationConfigApplicationContext current = new AnnotationConfigApplicationContext();
        current.setId("current-context");
        // 3. current -> Parent
        current.setParent(parent);
        // 注册 MyListener 到 Current
        current.register(MyListener.class);

        // 4. 启动 Parent
        parent.refresh();
        // 5. 启动 Current
        current.refresh();

        // 关闭 应用上下文
        current.close();
        parent.close();
    }

    static class MyListener implements ApplicationListener<ApplicationContextEvent> {

        private static Set<ApplicationContextEvent> processedEvents = new LinkedHashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if (processedEvents.add(event)) {
                System.out.printf("监听 Spring 应用上下文[ID: %s]事件: %s\n", event.getApplicationContext().getId(),
                        event.getClass().getSimpleName());
            }
        }
    }
}
