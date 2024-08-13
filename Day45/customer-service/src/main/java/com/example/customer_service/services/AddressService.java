package com.example.customer_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customer_service.entities.Address;
import com.example.customer_service.repositories.AddressRepository;


@Service
public class AddressService {
    @Autowired
    private AddressRepository adressRepository;

    public void save(Address adress) {
        adressRepository.save(adress);
    }

    public List<Address> findAll() {
        return adressRepository.findAll();
    }

    public Address findById(Long id) {
        return adressRepository.findById(id).get();
    }

    public void update(Address entity, Address address) {
        entity.setClient(address.getClient());
        entity.setNumber(address.getNumber());
        entity.setStreet(address.getStreet());
        entity.setState(address.getState());
        entity.setCity(address.getCity());
    }

    public void deleteById(Long id) {
        adressRepository.deleteById(id);
    }
}
