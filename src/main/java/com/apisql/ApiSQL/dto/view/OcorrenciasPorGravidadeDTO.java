package com.apisql.ApiSQL.dto.view;

public class OcorrenciasPorGravidadeDTO {
    private Integer mes;
    private Integer ano;
    private Integer idUnidade;
    private Integer idLocalidade;
    private Long totalOcorrencias;
    private String gravidade;

    public OcorrenciasPorGravidadeDTO() {
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
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

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public Long getTotalOcorrencias() {
        return totalOcorrencias;
    }

    public void setTotalOcorrencias(Long totalOcorrencias) {
        this.totalOcorrencias = totalOcorrencias;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }
}