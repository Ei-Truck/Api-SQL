package com.apisql.ApiSQL.dto.view;

import jakarta.persistence.Column;

public class TotalOcorrenciasDTO {
    private Long total_ocorrencias;
    private Integer mes;
    private Integer ano;
    @Column(name = "id_unidade")
    private Integer idUnidade;
    @Column(name = "id_segmento")
    private Integer idSegmento;
    @Column(name = "id_localidade")
    private Integer idLocalidade;
    private String ufEstado;


    public Long getTotal_ocorrencias() {
        return total_ocorrencias;
    }
    public Integer getMes() {
        return mes;
    }
    public Integer getAno() {
        return ano;
    }

    public void setTotal_ocorrencias(Long total_ocorrencias) {
        this.total_ocorrencias = total_ocorrencias;
    }
    public void setMes(Integer mes) {
        this.mes = mes;
    }
    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Integer getIdSegmento() {
        return idSegmento;
    }

    public void setIdSegmento(Integer idSegmento) {
        this.idSegmento = idSegmento;
    }

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public String getUfEstado() {
        return ufEstado;
    }

    public void setUfEstado(String ufEstado) {
        this.ufEstado = ufEstado;
    }
}