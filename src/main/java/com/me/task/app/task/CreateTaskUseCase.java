package com.me.task.app.task;

import com.me.task.domain.task.Task;

public class CreateTaskUseCase {

    private final TaskRepository repository;

    public CreateTaskUseCase (TaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutPut execute (String description) {

        Task task = new Task(null, description);

        repository.save(task);

        TaskOutPut output = new TaskOutPut(task.getId(), task.getDescription(), task.getStatus().name());

        return output;

    }

}
