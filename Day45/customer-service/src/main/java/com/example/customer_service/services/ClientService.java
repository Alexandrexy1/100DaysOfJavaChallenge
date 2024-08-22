package com.example.customer_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.customer_service.entities.Client;
import com.example.customer_service.repositories.ClientRepository;


@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
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
