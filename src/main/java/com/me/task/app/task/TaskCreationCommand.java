package com.me.task.app.task;

public record TaskCreationCommand(

        String requestId,
        String description

) {
}
