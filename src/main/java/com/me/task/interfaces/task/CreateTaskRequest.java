package com.me.task.interfaces.task;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Object used to create a task")
public record CreateTaskRequest(

        @Schema(description = "Task description", example = "Estudar Spring Boot", requiredMode = Schema.RequiredMode.REQUIRED)
        String description

) {
}
