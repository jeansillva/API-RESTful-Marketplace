package com.example.marketplace.dtos;

import jakarta.validation.constraints.NotBlank;

public record VendedorRecordDto(
    @NotBlank String nome,
    @NotBlank String cnpj,
    @NotBlank String endereco,
    @NotBlank String email,
    @NotBlank String senha
) {}

