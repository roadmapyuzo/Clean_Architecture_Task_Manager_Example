package com.me.task;

import com.me.task.app.task.CreateTaskUseCase;
import com.me.task.app.task.TaskRepository;
import com.me.task.domain.task.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

        Task task = createTaskUseCase.execute("Studiyng");

        assertNotNull(task);
        assertEquals("Intentional error", task.getDescription());

        verify(repository, times(1)).save(task);

    }

    @Test
    void CreateTaskBlankDescriptionTest() {

        assertThrows(IllegalArgumentException.class, () -> createTaskUseCase.execute(""));

        verify(repository, never()).save(any(Task.class));

    }

}
