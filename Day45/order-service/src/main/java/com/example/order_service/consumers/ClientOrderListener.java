package com.example.order_service.consumers;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order_service.DTO.OrderDTO;
import com.example.order_service.Services.OrderService;
import com.rabbitmq.client.Channel;


@Service
public class ClientOrderListener {
    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "${spring.rabbitmq.receive.order.queue}", ackMode = "MANUAL")
    public void receiveOrder(OrderDTO orderDTO, Channel channel, Message message) throws IOException {
        try {
            orderService.convertToOrder(orderDTO);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch(NullPointerException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            throw new RuntimeException(e.getCause());
        }  
    }
}
