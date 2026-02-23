package com.me.task.app.task;

import com.me.task.app.DomainEventHandler;
import com.me.task.app.OutboxPublisher;
import com.me.task.domain.DomainEvent;
import com.me.task.domain.task.CompleteTaskEvent;

public class CompleteTaskHandler implements DomainEventHandler<CompleteTaskEvent> {

    private final OutboxPublisher publisher;

    public CompleteTaskHandler(OutboxPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean supports(DomainEvent event) {
        return event instanceof CompleteTaskEvent;
    }

    @Override
    public void handle(CompleteTaskEvent event) {

        CompleteTaskIntegrationEvent integrationEvent = CompleteTaskIntegrationEvent.fromDomain(event);
        publisher.publish(integrationEvent);

    }

}
