package com.apisql.ApiSQL.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

public class LocalidadeResponseDTO {

    private Integer id;
    private String ufEstado;

    public LocalidadeResponseDTO(Integer id, String ufEstado) {
        this.id = id;
        this.ufEstado = ufEstado;
    }

    public LocalidadeResponseDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUfEstado() { return ufEstado; }
    public void setUfEstado(String ufEstado) { this.ufEstado = ufEstado; }
}