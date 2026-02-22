package com.me.task.domain.task;

import com.me.task.domain.DomainEvent;

import java.time.LocalDateTime;

public class CompleteTaskEvent implements DomainEvent {

    private final Integer taskId;
    private final LocalDateTime occurredOn;

    public CompleteTaskEvent(Integer taskId) {
        this.taskId = taskId;
        this.occurredOn = LocalDateTime.now();
    }

    @Override
    public LocalDateTime occurredOn() {
        return occurredOn;
    }

    public Integer getTaskId() {
        return taskId;
    }
}
