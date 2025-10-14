package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SegmentoRequestDTO {
    @NotBlank(message = "O nome do segmento é obrigatório")
    @Size(max = 40, message = "O nome não pode ter mais de 40 caracteres")
    private String nome;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}