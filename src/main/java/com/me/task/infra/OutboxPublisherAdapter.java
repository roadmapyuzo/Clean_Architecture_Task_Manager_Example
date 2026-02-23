package com.me.task.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.task.app.IntegrationEvent;
import com.me.task.app.OutboxPublisher;

public class OutboxPublisherAdapter implements OutboxPublisher {

    private final OutBoxInMemoryRepository repository;
    private final ObjectMapper objectMapper;

    public OutboxPublisherAdapter(OutBoxInMemoryRepository repository) {
        this.repository = repository;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void publish(IntegrationEvent event) {
        try {
            String payload = serialize(event);
            OutboxMessage message = OutboxMessage.from(event.getId(), event.getType(), payload, event.occurredOn());
            repository.save(message);
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String serialize(IntegrationEvent event) throws Exception{
        return objectMapper.writeValueAsString(event);
    }
}
