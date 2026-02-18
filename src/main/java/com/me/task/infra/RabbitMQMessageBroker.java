package com.me.task.infra;

import com.me.task.app.task.MessageBroker;
import com.me.task.app.task.TaskCreationCommand;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMQMessageBroker implements MessageBroker {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQMessageBroker(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(TaskCreationCommand command) {

        rabbitTemplate.convertAndSend("task.creation.queue", command);

    }


}
