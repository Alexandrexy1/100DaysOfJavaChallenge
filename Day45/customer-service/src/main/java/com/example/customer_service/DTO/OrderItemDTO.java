package com.example.customer_service.DTO;

import java.math.BigDecimal;

public class OrderItemDTO {
    private Long id;
    private String name;
    private BigDecimal quantity;
    private BigDecimal unitPrice;

    public OrderItemDTO(Long id, String name, BigDecimal quantity, BigDecimal unitPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
