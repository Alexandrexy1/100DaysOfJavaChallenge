package com.example.customer_service.config;

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

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

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
    MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    Queue sendOrderQueue() {
        System.out.println("enviei uma queue no customer-service.");
        return new Queue(sendOrderQueue,true);
    }

    @Bean
    Queue receiveOrderQueue() {
        System.out.println("recebi uma queue no customer-service.");
        return new Queue(receiveOrderQueue, true);
    }

     @Bean
    DirectExchange orderExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding sendOrderBinding(@Qualifier("sendOrderQueue") Queue sendOrderQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(sendOrderQueue).to(orderExchange).with(routingKey);
    }

    @Bean
    Binding receiveOrderBinding(@Qualifier("receiveOrderQueue") Queue sendOrderQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(sendOrderQueue).to(orderExchange).with(routingKey);
    }
}
