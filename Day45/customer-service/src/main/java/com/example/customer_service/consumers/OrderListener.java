package com.example.customer_service.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customer_service.DTO.OrderDTO;
import com.example.customer_service.services.ClientService;

@Service
public class OrderListener {
    @Autowired
    ClientService clientService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveOrder(OrderDTO orderDTO) {
        System.out.println("Order received: " + orderDTO.getId());
    }
}
