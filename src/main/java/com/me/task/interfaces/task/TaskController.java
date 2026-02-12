package com.me.task.interfaces.task;

import com.me.task.app.task.CompleteTaskUseCase;
import com.me.task.app.task.CreateTaskUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final CompleteTaskUseCase completeTaskUseCase;
    private final CreateTaskUseCase createTaskUseCase;

    public TaskController(CompleteTaskUseCase completeTaskUseCase, CreateTaskUseCase createTaskUseCase) {
        this.completeTaskUseCase = completeTaskUseCase;
        this.createTaskUseCase = createTaskUseCase;
    }





}
