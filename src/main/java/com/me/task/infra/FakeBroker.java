package com.me.task.infra;

import com.me.task.app.task.MessageBroker;
import com.me.task.app.task.TaskCreationCommand;

public class FakeBroker implements MessageBroker {

    private final TaskCreationConsumer consumer;

    public FakeBroker(TaskCreationConsumer consumer) {

        this.consumer = consumer;

    }

    @Override
    public void publish(TaskCreationCommand message) {

        System.out.println("Publishing message: " + message);

        new Thread(() -> consumer.consume(message)).start();

    }

    @Override
    public void publishWithExchange(String exchange, String routingKey, TaskCreationCommand command) {

        System.out.println("Publishing message: " + command);

    }
}
