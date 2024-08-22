package com.example.product_service.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.product_service.entities.Product;

@Component
public class ProductProducer {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${spring.rabbitmq.receive.order.queue}")
    private String routingKey;

    public void publishMessageProduct(Product product) {
        rabbitTemplate.convertAndSend("", routingKey, product.getName() + " id: " + product.getId());
    }
}
