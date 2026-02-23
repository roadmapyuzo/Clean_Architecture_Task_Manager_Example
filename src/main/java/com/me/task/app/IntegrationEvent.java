package com.me.task.app;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IntegrationEvent {

    UUID getId();

    LocalDateTime occurredOn();

    String getType();


}
