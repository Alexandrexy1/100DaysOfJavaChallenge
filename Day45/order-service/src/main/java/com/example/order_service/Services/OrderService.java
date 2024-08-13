package com.example.order_service.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order_service.entities.Order;
import com.example.order_service.entities.OrderItem;
import com.example.order_service.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void saveOrderItem(OrderItem orderItem, Long id) {
        Order order = orderRepository.findById(id).get();
        orderItem.setOrder(order);
        order.setItems(orderItem);
        order.addTotal(orderItem.getUnitPrice(), orderItem.getQuantity());
        save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<OrderItem> findByIdAllOrderItem(Long id) {
        Order order = orderRepository.findById(id).get();
        return order.getItems();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    public void update(Order entity, Order order) {
        entity.setOrderDate(order.getOrderDate());
        entity.setStatus(order.getStatus());
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
