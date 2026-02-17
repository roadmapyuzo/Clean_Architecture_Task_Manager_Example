package com.me.task.infra;

import com.me.task.app.task.TaskRepository;
import com.me.task.domain.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryTaskRepository implements TaskRepository {

    private final Map<Integer, Task> database = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @Override
    public Task findById(Integer id) {
        return database.get(id);
    }

    @Override
    public void save(Task task) {

        if (task.getId() == null) {
            task.assignId(idGenerator.getAndIncrement());
        }

        database.put(task.getId(), task);

    }

    @Override
    public List<Task> findAll() {

        return new ArrayList<>(database.values());

    }

}
