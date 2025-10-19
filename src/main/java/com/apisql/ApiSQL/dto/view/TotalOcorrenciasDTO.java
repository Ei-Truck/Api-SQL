package com.apisql.ApiSQL.dto.view;

public class TotalOcorrenciasDTO {
    private Long total_ocorrencias;
    private Integer mes;
    private Integer ano;

    public TotalOcorrenciasDTO() {}

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
}