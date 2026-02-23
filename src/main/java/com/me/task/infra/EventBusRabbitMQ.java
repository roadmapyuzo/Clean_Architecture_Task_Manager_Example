package com.me.task.infra;

import com.me.task.app.EventBus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

public class EventBusRabbitMQ implements EventBus {

    private final OutBoxInMemoryRepository repository;
    private final RabbitTemplate rabbitTemplate;
    private final EventRouter eventRouter;

    public EventBusRabbitMQ(OutBoxInMemoryRepository repository,  RabbitTemplate rabbitTemplate,  EventRouter eventRouter) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
        this.eventRouter = eventRouter;
    }

    public void publishPending() {

        List<OutboxMessage> messages = repository.findPending();

        for (OutboxMessage message : messages) {

            String queue = eventRouter.resolveDestination(message.getType());

            rabbitTemplate.convertAndSend(message.getType(), message.getPayload());
            message.markAsPublished();

        }


    }

}
