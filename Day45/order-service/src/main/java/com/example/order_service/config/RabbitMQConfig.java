package com.example.order_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    @Value("${spring.rabbitmq.send.order.queue}")
    private String sendOrderQueue;

    @Value("${spring.rabbitmq.send.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.send.routingkey}")
    private String routingKey;

    @Value("${spring.rabbitmq.receive.order.queue}")
    private String receiveOrderQueue;

    @Bean
    DirectExchange orderExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Queue sendOrderQueue() {
        return new Queue(sendOrderQueue,true);
    }

    @Bean
    Queue receiveOrderQueue() {
        return new Queue(receiveOrderQueue, true);
    }

    @Bean
    Binding sendOrderBinding(@Qualifier("sendOrderQueue") Queue orderQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(routingKey);
    }
    
    @Bean
    Binding receiveOrderBinding(@Qualifier("receiveOrderQueue") Queue orderQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(routingKey);
    }

    @Bean
    MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
