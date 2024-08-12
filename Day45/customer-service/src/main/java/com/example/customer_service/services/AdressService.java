package com.example.customer_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.customer_service.entities.Adress;
import com.example.customer_service.repositories.AdressRepository;

import jakarta.persistence.Entity;

@Entity
public class AdressService {
    @Autowired
    private AdressRepository adressRepository;

    public void save(Adress adress) {
        adressRepository.save(adress);
    }

    public List<Adress> findAll() {
        return adressRepository.findAll();
    }

    public Adress findById(Long id) {
        return adressRepository.findById(id).get();
    }

    public void update(Adress entity, Adress client) {
        entity.setClient(client.getClient());
        entity.setNumber(client.getNumber());
        entity.setStreet(client.getStreet());
        entity.setState(client.getState());
        entity.setCity(client.getCity());
    }

    public void deleteById(Long id) {
        adressRepository.deleteById(id);
    }
}
