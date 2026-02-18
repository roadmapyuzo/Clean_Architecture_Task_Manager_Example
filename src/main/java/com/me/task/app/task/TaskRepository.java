package com.me.task.app.task;

import com.me.task.domain.task.Task;

import java.util.List;

public interface TaskRepository {

    Task findById(Integer id);

    void save(Task task);

    List<Task> findAll();

    boolean saveProcessedId(String requestid);

    boolean verifyProcessedId(String requestid);

}
