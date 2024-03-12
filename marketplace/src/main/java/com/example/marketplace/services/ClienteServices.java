package com.example.marketplace.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.marketplace.models.ClienteModel;
import com.example.marketplace.repositories.ClienteRepository;


@Service
public class ClienteServices {
    
    @Autowired
    ClienteRepository clienteRepository;

    public List<ClienteModel> findAll(){
        return clienteRepository.findAll();
    }

    public Optional<ClienteModel> findById(Long id){
        return clienteRepository.findById(id);
    }

    public ClienteModel saveCliente(ClienteModel clienteModel){
        return clienteRepository.save(clienteModel);
    }

    public void deleteCliente(Long id){
        clienteRepository.deleteById(id);
    }

}
