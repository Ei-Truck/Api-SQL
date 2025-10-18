package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CargoRequestDTO {
    @NotBlank(message = "O nome do cargo é obrigatório")
    @Size(max = 255, message = "O nome não pode ter mais de 255 caracteres")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}