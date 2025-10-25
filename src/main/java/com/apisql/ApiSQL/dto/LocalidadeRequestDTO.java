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

    @Size(max = 100, message = "O nome da rua não pode ter mais de 100 caracteres")
    private String rua;

    @Size(max = 50, message = "O bairro não pode ter mais de 50 caracteres")
    private String bairro;

    @Size(max = 50, message = "A cidade não pode ter mais de 50 caracteres")
    private String cidade;

    @Size(max = 50, message = "O país não pode ter mais de 50 caracteres")
    private String pais;

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public Integer getNumeroRua() { return numeroRua; }
    public void setNumeroRua(Integer numeroRua) { this.numeroRua = numeroRua; }

    public String getUfEstado() { return ufEstado; }
    public void setUfEstado(String ufEstado) { this.ufEstado = ufEstado; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
}
