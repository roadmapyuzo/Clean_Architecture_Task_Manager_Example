package com.me.task.app.task;

import com.me.task.app.DomainEventDispatcher;
import com.me.task.domain.DomainEvent;
import com.me.task.domain.task.Task;

import java.util.List;

public class CompleteTaskUseCase {

    private final TaskRepository repository;

    private final DomainEventDispatcher eventDispatcher;

    public CompleteTaskUseCase(TaskRepository repository,  DomainEventDispatcher eventDispatcher) {
        this.repository = repository;
        this.eventDispatcher = eventDispatcher;
    }

    public TaskOutPut execute(Integer taskId) {

        Task task = repository.findById(taskId);

        if (task == null) {
            throw new TaskNotFoundException(taskId);
        }

        task.completeTask();

        repository.save(task);

        List<DomainEvent> events = task.pullEvents();
        eventDispatcher.dispatch(events);

        return new TaskOutPut(task.getId(), task.getDescription(), task.getStatus().name());

    }

}
