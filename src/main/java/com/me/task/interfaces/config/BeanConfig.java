package com.me.task.interfaces.config;

import com.me.task.app.DomainEventDispatcher;
import com.me.task.app.DomainEventHandler;
import com.me.task.app.EventBus;
import com.me.task.app.OutboxPublisher;
import com.me.task.app.task.*;
import com.me.task.infra.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Configuration
public class BeanConfig {

    @Bean
    public EventRouter eventRouter() {
        return new RabbitRouter();
    }


    @Bean
    public OutBoxInMemoryRepository outBoxInMemoryRepository() {
        return new OutBoxInMemoryRepository();
    }

    @Bean
    public OutboxPublisher outboxPublisher(OutBoxInMemoryRepository outBoxInMemoryRepository) {
        return new OutboxPublisherAdapter(outBoxInMemoryRepository);
    }

    @Bean
    public EventBus eventBus(OutBoxInMemoryRepository outBoxInMemoryRepository , RabbitTemplate rabbitTemplate,EventRouter eventRouter) {
        return new EventBusRabbitMQ(outBoxInMemoryRepository,rabbitTemplate,eventRouter);
    }

    @Bean
    public OutboxScheduler outboxScheduler(EventBus eventBus) {
        return new OutboxScheduler(eventBus);
    }

    @Bean
    public CompleteTaskHandler completeTaskHandler(OutboxPublisher outboxPublisher) {
        return new CompleteTaskHandler(outboxPublisher);
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
