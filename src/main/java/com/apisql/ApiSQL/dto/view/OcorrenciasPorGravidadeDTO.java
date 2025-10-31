package com.apisql.ApiSQL.dto.view;

import jakarta.persistence.Column;

public class OcorrenciasPorGravidadeDTO {
    @Column(name = "id_unidade")
    private Integer idUnidade;
    @Column(name = "id_segmento")
    private Integer idSegmento;
    @Column(name = "id_localidade")
    private Integer idLocalidade;
    private Long total_ocorrencias;
    private String gravidade;
    private Integer mes;
    private Integer ano;
    private String estado;

    public OcorrenciasPorGravidadeDTO() {}

    public Long getTotal_ocorrencias() {
        return total_ocorrencias;
    }
    public String getGravidade() {
        return gravidade;
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
    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
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

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}