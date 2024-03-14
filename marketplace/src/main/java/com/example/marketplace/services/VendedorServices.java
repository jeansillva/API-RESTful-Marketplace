package com.example.marketplace.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.marketplace.dtos.VendedorRecordDto;
import com.example.marketplace.models.VendedorModel;
import com.example.marketplace.repositories.VendedorRepository;

@Service
public class VendedorServices {
    
    @Autowired
    VendedorRepository vendedorRepository;

    public List<VendedorModel> findAll(){
        return vendedorRepository.findAll();
    }

    public Optional<VendedorModel> findById(Long id){
        return vendedorRepository.findById(id);
    }
    
    public VendedorModel saveVendedor(VendedorModel vendedorModel){
        return vendedorRepository.save(vendedorModel);
    }

    public void deleteVendedor(Long id){
        vendedorRepository.deleteById(id);
    }

    public VendedorModel convertDto(VendedorRecordDto vendedorRecordDto){
        var vendedorModel = new VendedorModel();
        BeanUtils.copyProperties(vendedorRecordDto, vendedorModel);
        return vendedorModel;
    }
}
