package com.example.order_service.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;

    public ProductDTO() {}

    public ProductDTO(String name, String description, BigDecimal price, Integer stockQuantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    
}
