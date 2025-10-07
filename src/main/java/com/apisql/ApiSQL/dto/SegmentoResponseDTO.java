// SegmentoDTO.java
package com.apisql.ApiSQL.dto;

import com.apisql.ApiSQL.model.Segmento;

public class SegmentoDTO {

    private Integer id;
    private String nome;

    public SegmentoDTO() {
    }

    public SegmentoDTO(Segmento segmento) {
        if (segmento != null) {
            this.id = segmento.getId();
            this.nome = segmento.getNome();
        }
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
