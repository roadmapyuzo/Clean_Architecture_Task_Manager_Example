package com.me.task.app.task;

import com.me.task.domain.task.Task;

public interface TaskRepository {

    Task findById(Integer id);

    void save(Task task);

}
