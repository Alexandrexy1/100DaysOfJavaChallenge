package com.example.reporting_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reporting_service.entities.SalesReport;
import com.example.reporting_service.repositories.SalesReportRepository;

@Service
public class SalesReportService {
    @Autowired
    private SalesReportRepository repository;

    public void save(SalesReport salesReport) {
        repository.save(salesReport);
    }

    public List<SalesReport> findAll() {
        return repository.findAll();
    }

    public SalesReport findById(Long id) {
        return repository.findById(id).get();
    }
}
