package com.example.order_service.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.order_service.DTO.OrderDTO;
import com.example.order_service.DTO.OrderItemDTO;
import com.example.order_service.entities.Order;
import com.example.order_service.entities.OrderItem;
import com.example.order_service.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Order createOrderDTO(Long id) {
        Order order = findById(id);    
        OrderDTO orderDTO = convertToOrderDTO(order);
        rabbitTemplate.convertAndSend("orderExchange", "orderRoutingKey", orderDTO);
        return order; 
    }

    public OrderDTO convertToOrderDTO(Order order) {
        List<OrderItemDTO> orderItemDTOs = convertToOrderItemDTOList(order.getItems());
        
        return new OrderDTO(order.getId(),
        order.getCustomerId(), 
        order.getOrderDate(), 
        order.getStatus(), 
        order.getTotal(), 
        orderItemDTOs);
    }

    public Order convertToOrder(OrderDTO orderDTO) {
        List<OrderItem> orderItems = null;
        try {
            if (orderDTO.getItems() != null) orderItems = convertToOrderItemsList(orderDTO.getItems()); 
            Order order = new Order(orderDTO.getCustomerId());
            if (orderItems != null) order.setItems(orderItems);
            Order savedOrder = save(order);
    
            return savedOrder;
        } catch (NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }
    }



    private List<OrderItem> convertToOrderItemsList(List<OrderItemDTO> orderItemsDTO) {

        List<OrderItem> orderItems = new ArrayList<>();
        orderItemsDTO.forEach(orderItemDTO -> orderItems.add(orderItemService.convertToOrderItem(orderItemDTO)));
        return orderItems;
    }

    private List<OrderItemDTO> convertToOrderItemDTOList(List<OrderItem> orderItems) {
        List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
        orderItems.forEach(orderItem -> orderItemDTOs.add(orderItemService.convertToOrderItemDTO(orderItem)));
        return orderItemDTOs;
    }

    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void saveOrderItem(OrderItem orderItem, Long id) {
        Order order = orderRepository.findById(id).get();
        orderItem.setOrder(order);
        order.addItem(orderItem);
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
