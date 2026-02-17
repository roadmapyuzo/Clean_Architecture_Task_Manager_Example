package com.me.task.app.task;

import java.util.UUID;

public class RequestTaskCreationUseCase {

    private final MessageBroker broker;

    public RequestTaskCreationUseCase(MessageBroker broker) {
        this.broker = broker;
    }

    public String execute(String description) {

        String requestId = UUID.randomUUID().toString();

        TaskCreationCommand message = new TaskCreationCommand(requestId, description);

        broker.publish(message);

        return requestId;

    }

}
