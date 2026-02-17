package com.me.task.app.task;

public interface MessageBroker {

    void publish(TaskCreationCommand message);

}
