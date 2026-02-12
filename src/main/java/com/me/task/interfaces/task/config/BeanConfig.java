package com.me.task.interfaces.task.config;

import com.me.task.app.task.CompleteTaskUseCase;
import com.me.task.app.task.CreateTaskUseCase;
import com.me.task.app.task.TaskRepository;
import com.me.task.infra.InMemoryTaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public TaskRepository taskRepository() {
        return new InMemoryTaskRepository();
    }

    @Bean
    public CreateTaskUseCase createTaskUseCase(TaskRepository taskRepository) {
        return new CreateTaskUseCase(taskRepository);
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(TaskRepository taskRepository) {
        return new CompleteTaskUseCase(taskRepository);
    }



}
