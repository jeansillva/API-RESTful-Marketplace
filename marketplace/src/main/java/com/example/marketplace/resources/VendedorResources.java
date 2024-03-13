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

import com.example.marketplace.models.VendedorModel;
import com.example.marketplace.services.VendedorServices;

@RestController
@RequestMapping("/vendedor")
public class VendedorResources {
    
    @Autowired
    VendedorServices vendedorServices;

    @GetMapping
    public ResponseEntity<List<VendedorModel>> findAllVendedores(){
        List<VendedorModel> list = vendedorServices.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findVendedorById(@PathVariable Long id){
        Optional<VendedorModel> vendedorOptional = vendedorServices.findById(id);
        if(vendedorOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor não encontrado!");
        }
        VendedorModel vm = vendedorOptional.get();
        return ResponseEntity.status(HttpStatus.OK).body(vm);
    }

    @PostMapping("/post")
    public ResponseEntity<VendedorModel> saveVendedor(@RequestBody VendedorModel vendedorModel){
        VendedorModel vendedorSaved = vendedorServices.saveVendedor(vendedorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedorSaved);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Object> putVendedor(@PathVariable Long id, @RequestBody VendedorModel vendedorModel){
        Optional<VendedorModel> vendedorOptional = vendedorServices.findById(id);
        if(vendedorOptional.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor não encontrado!");
        }
        VendedorModel vm = vendedorServices.saveVendedor(vendedorModel);
        return ResponseEntity.status(HttpStatus.OK).body(vm);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id){
        Optional<VendedorModel> vendedorOptional = vendedorServices.findById(id);
        if(vendedorOptional.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor não encontrado!");
        }
        vendedorServices.deleteVendedor(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor excluído!");
    }
}
