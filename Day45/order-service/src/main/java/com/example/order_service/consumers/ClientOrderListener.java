package com.example.order_service.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class ClientOrderListener {

    @RabbitListener(queues = "${spring.rabbitmq.receive.order.queue}")
    public void listenOrderQueue(@Payload String name) {
        System.out.println("name: " + name);
    }
}
