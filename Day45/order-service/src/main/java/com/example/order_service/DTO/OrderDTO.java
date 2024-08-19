package com.example.order_service.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.order_service.entities.enums.OrderStatus;

public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long customerId;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private BigDecimal total;
    private List<OrderItemDTO> items;

    // Constructors
    public OrderDTO() {}

    public OrderDTO(Long id, Long customerId, LocalDateTime orderDate, OrderStatus status, BigDecimal total, List<OrderItemDTO> items) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.total = total;
        this.items = items;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void addTotal(BigDecimal total, BigDecimal quantity) {
        this.total.add(total.multiply(quantity));
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
