package io.github.hashmaparraylist.aop.features.event;

import org.springframework.context.ApplicationEvent;

/**
 * 动作已执行事件
 */
public class ExecutedEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ExecutedEvent(Object source) {
        super(source);
    }
}
