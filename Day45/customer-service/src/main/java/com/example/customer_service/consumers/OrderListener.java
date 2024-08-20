package com.example.customer_service.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customer_service.DTO.OrderDTO;
import com.example.customer_service.services.OrderService;


@Service
public class OrderListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "${spring.rabbitmq.receive.order.queue}")
    public void receiveOrder(OrderDTO orderDTO) {
        orderService.addOrderDTO(orderDTO);
        for(OrderDTO order: orderService.findAllOrderDTO()) {
            System.out.println(order.getCustomerId());
            System.out.println(order.getOrderDate());
        }
    }
}
