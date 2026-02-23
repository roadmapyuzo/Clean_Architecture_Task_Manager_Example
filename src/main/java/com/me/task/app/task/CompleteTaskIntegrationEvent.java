package com.me.task.app.task;

import com.me.task.app.IntegrationEvent;
import com.me.task.domain.task.CompleteTaskEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public class CompleteTaskIntegrationEvent implements IntegrationEvent {

    private final UUID integrationEventId = UUID.randomUUID();
    private final Integer TaskId;
    private final LocalDateTime occurredOn;

    public CompleteTaskIntegrationEvent(Integer taskId, LocalDateTime occurredOn) {
        this.TaskId = taskId;
        this.occurredOn = occurredOn;

    }

    public static CompleteTaskIntegrationEvent fromDomain(CompleteTaskEvent event) {
        return new CompleteTaskIntegrationEvent(event.getTaskId(),  event.occurredOn());
    }

    public String getType() {
        return "task.complete";
    }

    public UUID getId() {
        return integrationEventId;
    }

    public LocalDateTime occurredOn() {
        return occurredOn;
    }


}
