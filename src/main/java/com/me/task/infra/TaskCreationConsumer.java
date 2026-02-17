package com.me.task.infra;

import com.me.task.app.task.CreateTaskUseCase;
import com.me.task.app.task.TaskCreationCommand;

public class TaskCreationConsumer {

    private final CreateTaskUseCase createTaskUseCase;

    public TaskCreationConsumer(CreateTaskUseCase createTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
    }

    public void consume(TaskCreationCommand message) {

        System.out.println("Consuming message: " + message);

        createTaskUseCase.execute(message.description());

    }

}
