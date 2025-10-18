package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TipoGravidadeRequestDTO {
    @NotNull(message = "O ID é obrigatório, pois não é gerado automaticamente")
    private Integer id;

    @NotBlank(message = "O nome do TipoGravidade é obrigatório")
    @Size(max = 50, message = "O nome não pode ter mais de 50 caracteres")
    private String nome;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}