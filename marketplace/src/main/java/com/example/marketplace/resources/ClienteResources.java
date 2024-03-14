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

import com.example.marketplace.models.ClienteModel;
import com.example.marketplace.services.ClienteServices;


@RestController
@RequestMapping(value = "/cliente")
public class ClienteResources {

    @Autowired
    ClienteServices clienteServices;

    @GetMapping
    public ResponseEntity<List<ClienteModel>> findAll(){
        List<ClienteModel> list = clienteServices.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<ClienteModel> clienteOptional = clienteServices.findById(id);
        if(clienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        ClienteModel cm = clienteOptional.get();
        return ResponseEntity.status(HttpStatus.OK).body(cm);
    }

    @PostMapping("/post")
    public ResponseEntity<ClienteModel> createCliente(@RequestBody ClienteModel clienteModel){
        ClienteModel savedCliente = clienteServices.saveCliente(clienteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Object> modifyCliente(@PathVariable Long id, @RequestBody ClienteModel clienteModel){
        Optional<ClienteModel> clienteOptional = clienteServices.findById(id);
        if(clienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        ClienteModel cm = clienteServices.saveCliente(clienteModel);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente Alterado com sucesso!\n" +cm);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id){
        Optional<ClienteModel> optionalCliente = clienteServices.findById(id);
        if(optionalCliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        clienteServices.deleteCliente(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso!");
    }

    
}
