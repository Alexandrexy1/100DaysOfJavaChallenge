package com.example.order_service.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.order_service.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;

    private LocalDateTime orderDate;
    private OrderStatus status;
    private BigDecimal total;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    public Order() {}

    public Order(Long customerId) {
        this.customerId = customerId;
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
        this.total = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
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

    public void addTotal(BigDecimal unitPrice, BigDecimal quantity) {
        total = total.add(unitPrice.multiply(quantity));
    }

    public Long getCustomerId() {
        return customerId;
    } 
}

