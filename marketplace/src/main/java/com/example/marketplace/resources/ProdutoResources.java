package com.example.marketplace.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.marketplace.dtos.ProdutoRecordDto;
import com.example.marketplace.models.ClienteModel;
import com.example.marketplace.models.ProdutoModel;
import com.example.marketplace.services.ProdutoServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoResources {
    
    @Autowired
    ProdutoServices produtoServices;

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> getAllProdutos(){
        List<ProdutoModel> produtoList = produtoServices.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(produtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProdutoById(@PathVariable Long id){
        Optional<ProdutoModel> produtoOptional = produtoServices.findById(id);
        if(produtoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        ProdutoModel pm = produtoOptional.get();
        return ResponseEntity.status(HttpStatus.OK).body(pm);
    }
    
    @PostMapping("/post")
    public ResponseEntity<ProdutoModel> saveProduto(@RequestBody @Valid ProdutoRecordDto produtoRecordDto){
        ProdutoModel produtoModel = produtoServices.convertDto(produtoRecordDto);
        ProdutoModel savedProduto = produtoServices.saveProduto(produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduto);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Object> putProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRecordDto produtoRecordDto){
        Optional<ProdutoModel> produtoOptional = produtoServices.findById(id);
        if(produtoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        ProdutoModel produtoModel = produtoServices.convertDto(produtoRecordDto);
        produtoModel.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(produtoServices.saveProduto(produtoModel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable Long id){
        Optional<ProdutoModel> produtoOptional = produtoServices.findById(id);
        if(produtoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
        produtoServices.deleteProduto(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluído!");
    }
}
