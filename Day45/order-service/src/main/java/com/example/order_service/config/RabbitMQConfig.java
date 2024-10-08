package com.example.order_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.receive.order.queue}")
    private String queue;
    
    @Bean
    Queue queue() {
        return new Queue(queue, true);
    }
}
