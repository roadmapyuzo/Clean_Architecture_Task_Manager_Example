package com.me.task.app.task;

import com.me.task.domain.task.Task;

import java.util.ArrayList;
import java.util.List;

public class ListTaskUseCase {

    private final TaskRepository repository;

    public ListTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskOutPut> execute() {

        List<TaskOutPut> res = new ArrayList<>();

        List<Task> tasks = repository.findAll();

        for (Task task : tasks) {

            TaskOutPut output = new TaskOutPut(task.getId(), task.getDescription(), task.getStatus().name());

            res.add(output);

        }

        return res;

    }

}
