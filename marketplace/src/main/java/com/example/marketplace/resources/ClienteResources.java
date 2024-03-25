package com.example.marketplace.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.marketplace.dtos.ClienteRecordDto;
import com.example.marketplace.models.ClienteModel;
import com.example.marketplace.services.ClienteServices;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;

@CrossOrigin(origins = {"http://localhost:8080", "https://jeansillva.com.br"})
@RestController
@RequestMapping(value = "/cliente")
public class ClienteResources {

    @Autowired
    ClienteServices clienteServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<ClienteModel>> findAll(){
        List<ClienteModel> list = clienteServices.findAll();
        if(!list.isEmpty()){
            for(ClienteModel clienteModel : list){
                Long id = clienteModel.getId();
                clienteModel.add(linkTo(methodOn(ClienteResources.class).findById(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<ClienteModel> clienteOptional = clienteServices.findById(id);
        if(clienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        ClienteModel cm = clienteOptional.get();
        cm.add(linkTo(methodOn(ClienteResources.class).findAll()).withRel("Lista de Clientes:"));
        return ResponseEntity.status(HttpStatus.OK).body(cm);
    }
    
    @PostMapping("/post")
    public ResponseEntity<ClienteModel> createCliente(@RequestBody @Valid ClienteRecordDto clienteRecordDto){
        ClienteModel cm = clienteServices.convertDto(clienteRecordDto);
        var password = passwordEncoder.encode(clienteRecordDto.senha());
        cm.setSenha(password);
        ClienteModel savedCliente = clienteServices.saveCliente(cm);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Object> modifyCliente(@PathVariable Long id, @RequestBody @Valid ClienteRecordDto clienteRecordDto){
        Optional<ClienteModel> clienteOptional = clienteServices.findById(id);
        if(clienteOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }
        ClienteModel clienteModel = clienteServices.convertDto(clienteRecordDto);
        clienteModel.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(clienteServices.saveCliente(clienteModel));
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
