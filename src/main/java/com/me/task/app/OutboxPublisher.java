package com.me.task.app;

public interface OutboxPublisher {

    void publish(IntegrationEvent event);

}
