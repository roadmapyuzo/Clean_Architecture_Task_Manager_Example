package com.me.task.infra.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue taskCreationQueue() {


        return new Queue("task.creation.queue", true);

    }

    @Bean
    public Queue taskTestQueue() {
        return new Queue("task.test.queue", true);
    }

    @Bean
    public DirectExchange taskExchange() {
        return new DirectExchange("task.exchange");
    }

    @Bean
    public Binding taskCreationBinding(Queue taskCreationQueue, DirectExchange taskExchange) {
        return BindingBuilder.bind(taskCreationQueue).to(taskExchange).with("task.test.key");
    }

    @Bean Binding taskTestBinding(Queue taskTestQueue, DirectExchange taskExchange) {
        return BindingBuilder.bind(taskTestQueue).to(taskExchange).with("task.test.key");
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {

        return new RabbitAdmin(connectionFactory);
    }



}
