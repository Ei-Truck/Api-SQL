package com.apisql.ApiSQL.dto;

import com.apisql.ApiSQL.model.Segmento;

public class SegmentoResponseDTO {

    private Integer id;
    private String nome;

    public SegmentoResponseDTO() {
    }

    public SegmentoResponseDTO(Segmento segmento) {
        this.id = segmento.getId();
        this.nome = segmento.getNome();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
