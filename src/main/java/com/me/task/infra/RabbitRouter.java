package com.me.task.infra;

import java.util.Map;

public class RabbitRouter implements EventRouter {

    private final Map<String, String> routingTable = Map.of(
            "task.completed", "task.outbox.queue"
    );

    @Override
    public String resolveDestination(String eventType) {

        String queue = routingTable.get(eventType);

        if (queue == null) {
            throw new IllegalArgumentException(
                    "No queue configured for event type: " + eventType
            );
        }

        return queue;
    }

}
