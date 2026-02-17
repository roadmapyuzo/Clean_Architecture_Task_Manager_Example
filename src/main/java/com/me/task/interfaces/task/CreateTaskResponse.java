package com.me.task.interfaces.task;

import com.me.task.app.task.TaskOutPut;
import com.me.task.domain.task.Task;

public record CreateTaskResponse(
        Integer id,
        String description,
        String status

) {

    public static CreateTaskResponse from(TaskOutPut output) {

        return new CreateTaskResponse(

                output.id(),
                output.description(),
                output.status()

        );

    }

}
