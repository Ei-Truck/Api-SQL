package com.apisql.ApiSQL.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_localidade")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Localidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cep", length = 10)
    private String cep;

    @Column(name = "numero")
    private Integer numeroRua;

    @Column(name = "rua")
    private String rua;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "estado", length = 2)
    private String ufEstado;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "pais")
    private String pais;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_inactive")
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