package com.me.task.interfaces.config;

import com.me.task.app.task.*;
import com.me.task.infra.FakeBroker;
import com.me.task.infra.InMemoryTaskRepository;
import com.me.task.infra.TaskCreationConsumer;
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
    public TaskCreationConsumer taskCreationConsumer(CreateTaskUseCase createTaskUseCase) {
        return new TaskCreationConsumer(createTaskUseCase);
    }

    @Bean
    public MessageBroker MessageBroker(TaskCreationConsumer taskCreationConsumer) {
        return new FakeBroker(taskCreationConsumer);
    }

    @Bean
    public RequestTaskCreationUseCase requestTaskCreationUseCase(MessageBroker messagebroker) {
        return new RequestTaskCreationUseCase(messagebroker);
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(TaskRepository taskRepository) {
        return new CompleteTaskUseCase(taskRepository);
    }

    @Bean
    public ListTaskUseCase listTaskUseCase(TaskRepository taskRepository) {
        return new ListTaskUseCase(taskRepository);
    }





}
