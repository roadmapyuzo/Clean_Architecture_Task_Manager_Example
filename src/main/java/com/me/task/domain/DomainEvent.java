package com.me.task.domain;

import java.time.LocalDateTime;

public interface DomainEvent {

    LocalDateTime occurredOn();

}
