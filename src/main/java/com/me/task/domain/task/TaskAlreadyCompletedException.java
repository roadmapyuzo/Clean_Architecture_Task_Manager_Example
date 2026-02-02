package com.me.task.domain.task;

public class TaskAlreadyCompletedException extends RuntimeException {

    public TaskAlreadyCompletedException () {
        super("Task already completed");
    }

}
