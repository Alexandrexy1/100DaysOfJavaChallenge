package com.example.customer_service.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.customer_service.DTO.OrderDTO;

@Service
public class OrderService {
    List<OrderDTO> orders = new ArrayList<>();

    public void addOrderDTO(OrderDTO orderDTO) {
        orders.add(orderDTO);
    }

    public List<OrderDTO> findAllOrderDTO() {
        return orders;
    }

    public List<OrderDTO> findAllOrderDTOOfCustomerId(Long customerId) {
        List<OrderDTO> ordersOfCustomer = new ArrayList<>();
        for(OrderDTO orderDTO: ordersOfCustomer) {
            if(orderDTO.getCustomerId() == customerId) ordersOfCustomer.add(orderDTO);
        }
        return ordersOfCustomer;
    }


}
