package com.example.order_service.controllers;

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

import com.example.order_service.Services.OrderService;
import com.example.order_service.entities.Order;
import com.example.order_service.entities.OrderItem;

@RequestMapping("/orders")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<OrderItem>> findByIdAllOrderItems(@PathVariable Long id) {
        List<OrderItem> orderItems = orderService.findByIdAllOrderItem(id);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<OrderItem> saveOrderItem(@RequestBody OrderItem orderItem, @PathVariable Long id) {
        orderService.saveOrderItem(orderItem, id);
        orderService.createOrderDTO(id);
        return new ResponseEntity<>(orderItem, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) {
        orderService.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
