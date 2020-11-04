package io.github.hashmaparraylist.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义 Spring 事件
 *
 * @author
 * @date 2020/11/4
 */
public class MySpringEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param message 事件消息
     */
    public MySpringEvent(String message) {
        super(message);
    }

    @Override
    public String getSource() {
        return (String) super.getSource();
    }

    public String getMessage() {
        return getSource();
    }
}
