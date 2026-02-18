package com.me.task.infra;

import com.me.task.app.task.CreateTaskUseCase;
import com.me.task.app.task.MessageBroker;
import com.me.task.app.task.TaskCreationCommand;
import com.me.task.app.task.TaskRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class TaskCreationConsumer {

    private final TaskRepository repository;
    private final CreateTaskUseCase createTaskUseCase;


    public TaskCreationConsumer(CreateTaskUseCase createTaskUseCase,  TaskRepository repository) {
        this.createTaskUseCase = createTaskUseCase;
        this.repository = repository;

    }

    @RabbitListener(queues = "task.creation.queue" )
    public void consume(TaskCreationCommand message) {

        System.out.println("Consuming message: " + message);

        boolean firstTimeProcessing = repository.saveProcessedId(message.requestId());
        if (!firstTimeProcessing) {
            return;
        }

        createTaskUseCase.execute(message.description());

    }

}
