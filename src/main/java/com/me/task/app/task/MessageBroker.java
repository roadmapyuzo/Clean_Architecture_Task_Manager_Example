package com.me.task.app.task;

public interface MessageBroker {

    void publish(TaskCreationCommand message);

    void publishWithExchange(String exchange, String routingKey, TaskCreationCommand message);

}
