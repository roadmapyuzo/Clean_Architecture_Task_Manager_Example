package com.me.task.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public void raise (DomainEvent event) {
        this.domainEvents.add(event);
    }

    public List<DomainEvent> pullEvents() {

        List<DomainEvent> events = List.copyOf(this.domainEvents);
        this.domainEvents.clear();
        return events;

    }

}
