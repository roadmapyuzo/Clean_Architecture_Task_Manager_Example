package com.me.task.domain.task;

public class Task {

    private Integer id;
    private String description;
    private TaskStatus status;

    public Task (Integer id, String description) {

        this.id = id;
        this.description = description;
        this.status = TaskStatus.ON_GOING;

    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Integer getId() {
        return id;
    }

    public void changeDescription (String description) {

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description can not be blank");
        }

        this.description = description;

    }

    public void completeTask () {

        if (status == TaskStatus.COMPLETED) {
            throw new TaskAlreadyCompletedException();
        }

        this.status = TaskStatus.COMPLETED;

    }

}
