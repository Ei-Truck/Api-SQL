package com.apisql.ApiSQL.dto.view;

public class OcorrenciasPorGravidadeDTO {
    private Long total_ocorrencias;
    private String gravidade;
    private Integer mes;
    private Integer ano;

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
}