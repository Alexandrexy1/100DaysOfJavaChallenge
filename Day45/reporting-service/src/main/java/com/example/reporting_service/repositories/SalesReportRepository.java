package com.example.reporting_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.reporting_service.entities.SalesReport;

public interface SalesReportRepository extends JpaRepository<SalesReport, Long> {
    
}
