package com.example.product_service.configs;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.receive.order.queue}")
    private String queue;

    @Bean
    Queue queue() {
        return new Queue(queue, true);
    }
}

