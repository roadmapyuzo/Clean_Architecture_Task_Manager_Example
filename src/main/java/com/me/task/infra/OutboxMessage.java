package com.me.task.infra;

import java.time.LocalDateTime;
import java.util.UUID;

public class OutboxMessage {

    private final UUID id;
    private final String type;
    private final String payload;
    private final LocalDateTime occurredOn;
    private boolean published;

    private OutboxMessage(UUID id, String type, String payload, LocalDateTime occurredOn) {
        this.id = id;
        this.type = type;
        this.payload = payload;
        this.occurredOn = occurredOn;
        this.published = false;
    }

    public static OutboxMessage from(UUID id, String type,String payload, LocalDateTime occurredOn) {
        return new OutboxMessage(
                id,
                type,
                payload,
                occurredOn
        );
    }

    public UUID getId() { return id; }
    public String getType() { return type; }
    public String getPayload() { return payload; }
    public LocalDateTime getOccurredOn() { return occurredOn; }
    public boolean isPublished() { return published; }


    public void markAsPublished() { this.published = true; }

    @Override
    public String toString() {
        return "OutboxMessage{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", occurredOn=" + occurredOn +
                ", published=" + published +
                '}';
    }


}
