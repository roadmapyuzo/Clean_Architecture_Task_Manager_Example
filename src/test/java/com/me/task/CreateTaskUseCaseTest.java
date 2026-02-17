package com.me.task;

import com.me.task.app.task.CreateTaskUseCase;
import com.me.task.app.task.TaskOutPut;
import com.me.task.app.task.TaskRepository;
import com.me.task.domain.task.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateTaskUseCaseTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private CreateTaskUseCase createTaskUseCase;

    @Test
    void CreateTaskTest() {

        TaskOutPut task = createTaskUseCase.execute("Studiyng");

        assertNotNull(task);
        assertEquals("Studiyng", task.description());

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);

        verify(repository, times(1)).save(captor.capture());

        Task savedTask = captor.getValue();

        assertEquals("Studiyng", savedTask.getDescription());

    }

    @Test
    void CreateTaskBlankDescriptionTest() {

        assertThrows(IllegalArgumentException.class, () -> createTaskUseCase.execute(""));

        verify(repository, never()).save(any(Task.class));

    }

}
