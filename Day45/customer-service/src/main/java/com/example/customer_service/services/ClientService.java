package com.example.customer_service.services;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.customer_service.DTO.OrderDTO;
import com.example.customer_service.entities.Client;
import com.example.customer_service.repositories.ClientRepository;


@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.receive.order.queue}")
    private String queue;

    public void sendOrder(Long customerId) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerId(customerId);
        rabbitTemplate.convertAndSend(queue, orderDTO);
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).get();
    }

    public void update(Client entity, Client client) {
        entity.setAddress(client.getAddress());
        entity.setEmail(client.getEmail());
        entity.setName(client.getName());
        entity.setNumber(client.getNumber());
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
