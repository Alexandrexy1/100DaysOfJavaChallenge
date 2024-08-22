package com.example.order_service.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.order_service.DTO.OrderDTO;



@Component
public class ClientOrderProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${spring.rabbitmq.send.order.queue}")
    private String routingKey;

    public void publishMessageClientOrder(OrderDTO order) {
        rabbitTemplate.convertAndSend("", routingKey, order);
    }

}


