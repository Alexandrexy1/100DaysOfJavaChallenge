package com.example.customer_service.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.customer_service.entities.Client;

@Component
public class ClientIdProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${spring.rabbitmq.receive.order.queue}")
    private String routingKey;

    public void publishMessageOrder(Client client) {
        rabbitTemplate.convertAndSend("", routingKey, client.getId());
    }
}
