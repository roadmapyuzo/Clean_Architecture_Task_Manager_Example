package com.me.task.interfaces.task;

import com.me.task.app.task.TaskOutPut;

import java.util.ArrayList;
import java.util.List;

public record ListTaskResponse(

        List<TaskResponse> tasks

) {

    public static ListTaskResponse from(List<TaskOutPut> tasks) {

        List<TaskResponse> response = new ArrayList<TaskResponse>();
        for (TaskOutPut task : tasks) {

            TaskResponse taskresponse = new TaskResponse(task.id(), task.description(), task.status());
            response.add(taskresponse);

        }

        return new ListTaskResponse(response);

    }

}
