package com.apisql.ApiSQL.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

public class LocalidadeResponseDTO {

    private Integer id;
    private String cep;
    private Integer numeroRua;
    private String rua;
    private String bairro;
    private String ufEstado;
    private String cidade;
    private String pais;
    private LocalDateTime updatedAt;
    private Boolean isInactive = false;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }
    public Integer getNumeroRua() { return numeroRua; }
    public void setNumeroRua(Integer numeroRua) { this.numeroRua = numeroRua; }
    public String getUfEstado() { return ufEstado; }
    public void setUfEstado(String ufEstado) { this.ufEstado = ufEstado; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
}