package com.example.customer_service.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.customer_service.DTO.OrderDTO;

@Component
public class OrderListener {

    @RabbitListener(queues = "${spring.rabbitmq.send.order.queue}")
    public void listenOrderQueue(@Payload OrderDTO orderDTO) {
        System.out.println(orderDTO);
    }
}
