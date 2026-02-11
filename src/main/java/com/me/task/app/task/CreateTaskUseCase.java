package com.me.task.app.task;

import com.me.task.domain.task.Task;

public class CreateTaskUseCase {

    private final TaskRepository repository;

    public CreateTaskUseCase (TaskRepository repository) {
        this.repository = repository;
    }

    public Task execute (String description) {

        Task task = new Task(null, description);

        repository.save(task);

        return task;

    }

}
