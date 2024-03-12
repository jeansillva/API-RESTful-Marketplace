package com.example.marketplace.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.marketplace.models.ProdutoModel;
import com.example.marketplace.repositories.ProdutoRepository;

@Service
public class ProdutoServices {
    
    @Autowired
    ProdutoRepository produtoRepository;

    public List<ProdutoModel> findAll(){
        return produtoRepository.findAll();
    }

    public Optional<ProdutoModel> findById(Long id){
        return produtoRepository.findById(id);
    }

    public ProdutoModel saveProduto(ProdutoModel produtoModel){
        return produtoRepository.save(produtoModel);
    }

    public void deleteProduto(Long id){
        produtoRepository.deleteById(id);
    }


}
