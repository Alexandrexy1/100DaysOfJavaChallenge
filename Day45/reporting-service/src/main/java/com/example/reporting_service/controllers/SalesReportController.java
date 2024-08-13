package com.example.reporting_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reporting_service.entities.SalesReport;
import com.example.reporting_service.services.SalesReportService;

@RequestMapping("sales-report")
@RestController
public class SalesReportController {
    @Autowired
    private SalesReportService service;

    @GetMapping
    public ResponseEntity<List<SalesReport>> findAll() {
        List<SalesReport> salesReports = service.findAll();
        return new ResponseEntity<>(salesReports, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesReport> findById(@PathVariable Long id) {
        SalesReport salesReport = service.findById(id);
        return new ResponseEntity<>(salesReport, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SalesReport> save(@RequestBody SalesReport salesReport) {
        service.save(salesReport);
        return new ResponseEntity<>(salesReport, HttpStatus.CREATED);
    } 
}
