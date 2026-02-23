package com.me.task.infra;

import com.me.task.app.EventBus;
import org.springframework.scheduling.annotation.Scheduled;

public class OutboxScheduler {

    private final EventBus eventBus;

    public OutboxScheduler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Scheduled(fixedRate = 10000)
    public void runProcess() {

        eventBus.publishPending();

    }

}
