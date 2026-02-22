package com.me.task.app;

import com.me.task.domain.DomainEvent;

import java.util.List;

public class DomainEventDispatcher {

    private final List<DomainEventHandler<?>> handlers;

    public DomainEventDispatcher(List<DomainEventHandler<?>> handlers) {
        this.handlers = handlers;
    }

    public <T extends DomainEvent> void invoke(
            DomainEventHandler<T> handler,
            DomainEvent event
    ) {
        handler.handle(((T) event));
    }

    public void dispatch(List<DomainEvent> events) {

        for (DomainEvent event : events) {

            for (DomainEventHandler<?> handler : handlers) {

                if (handler.supports(event)) {
                    invoke(handler, event);
                }

            }

        }

    }

}
