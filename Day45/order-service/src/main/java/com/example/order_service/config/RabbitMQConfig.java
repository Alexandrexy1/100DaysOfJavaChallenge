package com.example.order_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.receive.order.queue}")
    private String queue;

    @Bean
    DirectExchange orderExchange() {
        return new DirectExchange("orderExchange");
    }

    @Bean
    Queue orderQueue() {
        return new Queue(queue,true);
    }

    @Bean
    Binding orderBinding(Queue orderQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with("orderRoutingKey");
    }

    @Bean
    MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
