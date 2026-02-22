package com.me.task.app;

import com.me.task.domain.DomainEvent;

public interface DomainEventHandler<T extends DomainEvent> {

    boolean supports(DomainEvent event);

    void handle(T event);

}
