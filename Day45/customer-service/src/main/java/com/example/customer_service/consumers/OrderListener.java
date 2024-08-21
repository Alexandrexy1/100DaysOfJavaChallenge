package com.example.customer_service.consumers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.example.customer_service.DTO.OrderDTO;
import com.example.customer_service.services.OrderService;
import com.rabbitmq.client.Channel;


@Service
public class OrderListener {

    @Autowired
    private OrderService orderService;

        private static final Logger log = LoggerFactory.getLogger(OrderListener.class);

    @RabbitListener(queues = "${spring.rabbitmq.receive.order.queue}", ackMode = "MANUAL")
    public void receiveOrder(OrderDTO orderDTO, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            log.info("Recebido: {}", orderDTO);
            channel.basicAck(tag, false);
            System.out.println(orderDTO.getCustomerId());
        } catch(NullPointerException e) {
            channel.basicNack(tag, false, true);
            throw new RuntimeException(e.getCause());
        }  
    }
}
