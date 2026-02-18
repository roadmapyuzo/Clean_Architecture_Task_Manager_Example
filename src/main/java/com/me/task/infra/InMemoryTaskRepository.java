package com.me.task.infra;

import com.me.task.app.task.TaskRepository;
import com.me.task.domain.task.Task;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryTaskRepository implements TaskRepository {

    private final Map<Integer, Task> database = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);
    private final Set<String> processedIds = ConcurrentHashMap.newKeySet();

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

    @Override
    public boolean saveProcessedId(String requestid) {
        return processedIds.add(requestid);
    };

    @Override
    public boolean verifyProcessedId(String requestid) {
        return processedIds.contains(requestid);
    };

}
