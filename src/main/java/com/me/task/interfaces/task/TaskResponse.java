package com.me.task.interfaces.task;

public record TaskResponse(
        Integer id,
        String description,
        String status
) {}
