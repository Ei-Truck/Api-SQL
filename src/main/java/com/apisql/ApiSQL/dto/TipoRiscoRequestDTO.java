package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TipoRiscoRequestDTO {
    @NotBlank(message = "O nome do TipoRisco é obrigatório")
    @Size(max = 50, message = "O nome não pode ter mais de 50 caracteres")
    private String nome;
    private String descricao;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}