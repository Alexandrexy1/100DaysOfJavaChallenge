package com.example.order_service.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order_service.DTO.OrderDTO;
import com.example.order_service.Services.OrderService;


@Service
public class ClientOrderListener {
    @Autowired
    private OrderService orderService;
    

    @RabbitListener(queues = "${spring.rabbitmq.receive.order.queue}")
    public void receiveOrder(OrderDTO orderDTO) {
        try {
            orderService.convertToOrder(orderDTO);
        } catch(NullPointerException e) {
            throw new RuntimeException(e.getCause());
        }  
    }
}
