package com.example.customer_service.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.example.customer_service.DTO.OrderDTO;


@Service
public class OrderListener {

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveOrder(OrderDTO orderDTO) {
        System.out.println("Order received: " + orderDTO.getTotal());
    }
}
