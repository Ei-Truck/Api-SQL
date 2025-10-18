package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LocalidadeRequestDTO {
    @Size(max = 10, message = "CEP não pode ter mais de 10 caracteres")
    private String cep;
    private Integer numeroRua;
    @Size(max = 2, message = "UF deve ter 2 caracteres")
    private String ufEstado;
    @NotBlank(message = "O nome da localidade é obrigatório")
    @Size(max = 80, message = "O nome não pode ter mais de 80 caracteres")
    private String nome;

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public Integer getNumeroRua() { return numeroRua; }
    public void setNumeroRua(Integer numeroRua) { this.numeroRua = numeroRua; }
    public String getUfEstado() { return ufEstado; }
    public void setUfEstado(String ufEstado) { this.ufEstado = ufEstado; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}