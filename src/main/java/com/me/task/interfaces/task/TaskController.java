package com.me.task.interfaces.task;

import com.me.task.app.task.*;
import com.me.task.domain.task.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Tasks management")
public class TaskController {

    private final CompleteTaskUseCase completeTaskUseCase;
    private final CreateTaskUseCase createTaskUseCase;
    private final RequestTaskCreationUseCase requestTaskCreationUseCase;
    private final ListTaskUseCase listTaskUseCase;

    public TaskController(CompleteTaskUseCase completeTaskUseCase, CreateTaskUseCase createTaskUseCase,  RequestTaskCreationUseCase requestTaskCreationUseCase, ListTaskUseCase listTaskUseCase) {
        this.completeTaskUseCase = completeTaskUseCase;
        this.createTaskUseCase = createTaskUseCase;
        this.requestTaskCreationUseCase = requestTaskCreationUseCase;
        this.listTaskUseCase = listTaskUseCase;
    }

    @Operation(summary = "Create a task")
    @PostMapping
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody CreateTaskRequest request) {

        TaskOutPut output = createTaskUseCase.execute(request.description());

        return ResponseEntity.status(HttpStatus.CREATED).body(CreateTaskResponse.from(output));

    }

    @Operation(summary = "Create task using broker message")
    @PostMapping("/async")
    public ResponseEntity<String> createTaskAsync(@RequestBody CreateTaskRequest request) {

        String requestId = requestTaskCreationUseCase.execute(request.description());

        return ResponseEntity.accepted().body(requestId);

    }

    @Operation(summary = "Update task status")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<CreateTaskResponse> completeTask(@PathVariable Integer id) {

        TaskOutPut output = completeTaskUseCase.execute(id);

        return ResponseEntity.ok(CreateTaskResponse.from(output));
    }

    @GetMapping
    public ResponseEntity<ListTaskResponse> listTasks() {

        List<TaskOutPut> tasks = listTaskUseCase.execute();

        return ResponseEntity.ok(ListTaskResponse.from(tasks));

    }

    /// testing workflow 1054


}
