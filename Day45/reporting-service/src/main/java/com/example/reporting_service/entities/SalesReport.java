package com.example.reporting_service.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sales_report")
public class SalesReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime generationDate = LocalDateTime.now();
    private Integer totalSales;
    private Integer totalOrders;
    private Integer totalCustomers;

    public SalesReport() {}

    public SalesReport(Integer totalSales, Integer totalOrders, Integer totalCustomers) {
        this.totalSales = totalSales;
        this.totalOrders = totalOrders;
        this.totalCustomers = totalCustomers;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getGenerationDate() {
        return generationDate;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public Integer getTotalOrders() {
        return totalOrders;
    }

    public Integer getTotalCustomers() {
        return totalCustomers;
    } 
}
