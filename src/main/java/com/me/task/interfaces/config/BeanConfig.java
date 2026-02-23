package com.me.task.interfaces.config;

import com.me.task.app.DomainEventDispatcher;
import com.me.task.app.DomainEventHandler;
import com.me.task.app.task.*;
import com.me.task.infra.FakeBroker;
import com.me.task.infra.InMemoryTaskRepository;
import com.me.task.infra.RabbitMQMessageBroker;
import com.me.task.infra.TaskCreationConsumer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Configuration
public class BeanConfig {

    @Bean
    public CompleteTaskHandler completeTaskHandler() {
        return new CompleteTaskHandler();
    }

    @Bean
    public DomainEventDispatcher domainEventDispatcher(List<DomainEventHandler<?>> eventHandlers) {
        return new DomainEventDispatcher(eventHandlers);
    }

    @Bean
    public TaskRepository taskRepository() {
        return new InMemoryTaskRepository();
    }

    @Bean
    public CreateTaskUseCase createTaskUseCase(TaskRepository taskRepository) {
        return new CreateTaskUseCase(taskRepository);
    }


    @Bean
    public MessageBroker MessageBroker(RabbitTemplate rabbitTemplate) {

        return new RabbitMQMessageBroker(rabbitTemplate);
    }

    @Bean
    public TaskCreationConsumer taskCreationConsumer(CreateTaskUseCase createTaskUseCase,  TaskRepository repository) {
        return new TaskCreationConsumer(createTaskUseCase,  repository);
    }

    @Bean
    public RequestTaskCreationUseCase requestTaskCreationUseCase(MessageBroker messagebroker) {

        return new RequestTaskCreationUseCase(messagebroker);
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(TaskRepository taskRepository, DomainEventDispatcher domainEventDispatcher) {
        return new CompleteTaskUseCase(taskRepository, domainEventDispatcher);
    }

    @Bean
    public ListTaskUseCase listTaskUseCase(TaskRepository taskRepository) {
        return new ListTaskUseCase(taskRepository);
    }





}
