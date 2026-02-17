package com.me.task.app.task;

import com.me.task.domain.task.Task;

public class CompleteTaskUseCase {

    private final TaskRepository repository;

    public CompleteTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public TaskOutPut execute(Integer taskId) {

        Task task = repository.findById(taskId);

        if (task == null) {
            throw new TaskNotFoundException(taskId);
        }

        task.completeTask();

        repository.save(task);

        TaskOutPut output = new TaskOutPut(task.getId(), task.getDescription(), task.getStatus().name());

        return output;

    }

}
