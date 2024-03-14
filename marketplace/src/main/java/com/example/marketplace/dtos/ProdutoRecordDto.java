package com.example.marketplace.dtos;

import com.example.marketplace.models.ClienteModel;
import com.example.marketplace.models.VendedorModel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoRecordDto(
    @Size(min = 3, max = 100) String nome, 
    @NotNull Double preco, 
    @NotNull Long qtd,
    ClienteModel cliente,
    VendedorModel vendedor) {
}
