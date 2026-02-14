package com.me.task.interfaces.task;

import com.me.task.app.task.CompleteTaskUseCase;
import com.me.task.app.task.CreateTaskUseCase;
import com.me.task.domain.task.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Tasks management")
public class TaskController {

    private final CompleteTaskUseCase completeTaskUseCase;
    private final CreateTaskUseCase createTaskUseCase;

    public TaskController(CompleteTaskUseCase completeTaskUseCase, CreateTaskUseCase createTaskUseCase) {
        this.completeTaskUseCase = completeTaskUseCase;
        this.createTaskUseCase = createTaskUseCase;
    }

    @Operation(summary = "Create a task")
    @PostMapping
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody CreateTaskRequest request) {

        Task task = createTaskUseCase.execute(request.description());

        return ResponseEntity.status(HttpStatus.CREATED).body(CreateTaskResponse.from(task));

    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<CreateTaskResponse> completeTask(@PathVariable Integer id) {

        Task task = completeTaskUseCase.execute(id);

        return ResponseEntity.ok(CreateTaskResponse.from(task));
    }

    /// testing workflow


}
