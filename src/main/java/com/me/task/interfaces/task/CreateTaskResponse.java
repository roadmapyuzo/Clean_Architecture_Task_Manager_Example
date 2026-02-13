package com.me.task.interfaces.task;

import com.me.task.domain.task.Task;

public record CreateTaskResponse(
        Integer id,
        String description,
        String status

) {

    public static CreateTaskResponse from(Task task) {

        return new CreateTaskResponse(

                task.getId(),
                task.getDescription(),
                task.getStatus().toString()

        );

    }

}
