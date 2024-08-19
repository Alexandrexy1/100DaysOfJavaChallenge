package com.example.order_service.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order_service.DTO.OrderItemDTO;
import com.example.order_service.entities.OrderItem;
import com.example.order_service.repositories.OrderItemRepository;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public void save(OrderItem entity) {
        orderItemRepository.save(entity);
    }

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    public OrderItem findById(Long id) {
        return orderItemRepository.findById(id).get();
    }

    public void update(OrderItem entity, OrderItem orderItem) {
        entity.setOrder(orderItem.getOrder());
        entity.setQuantity(orderItem.getQuantity());
        entity.setUnitPrice(orderItem.getUnitPrice());
    }

    public OrderItemDTO convertToOrderItemDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO(
            orderItem.getId(), orderItem.getName(), orderItem.getQuantity(), orderItem.getUnitPrice());
        return orderItemDTO;
    }

    public OrderItem convertToOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem(
            orderItemDTO.getName(), orderItemDTO.getQuantity(), orderItemDTO.getUnitPrice());
        return orderItem;
    }

    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }
}
