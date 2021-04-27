package io.github.hashmaparraylist.aop.features.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class StaticExecutor implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public void execute() {
        System.out.println("Executing...");
        applicationEventPublisher.publishEvent(new ExecutedEvent(this));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
