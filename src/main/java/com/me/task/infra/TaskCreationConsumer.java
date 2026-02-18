package com.me.task.infra;

import com.me.task.app.task.CreateTaskUseCase;
import com.me.task.app.task.TaskCreationCommand;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class TaskCreationConsumer {

    private final CreateTaskUseCase createTaskUseCase;

    public TaskCreationConsumer(CreateTaskUseCase createTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
    }

    @RabbitListener(queues = "task.creation.queue" )
    public void consume(TaskCreationCommand message) {

        System.out.println("Consuming message: " + message);

        createTaskUseCase.execute(message.description());

    }

}
