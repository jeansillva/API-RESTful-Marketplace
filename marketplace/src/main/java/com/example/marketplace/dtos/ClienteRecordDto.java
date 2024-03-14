package com.example.marketplace.dtos;

import java.util.List;

import com.example.marketplace.models.ProdutoModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRecordDto(
    @Size(min = 3, max= 255) String nome, 
    @NotBlank String cpf, 
    @NotBlank String endereco, 
    @Email String email, 
    @NotBlank String senha,
    List<ProdutoModel> produtosComprados) {
}
