package com.apisql.ApiSQL.dto.view;

public class MotoristaQuantidadeInfracoesDTO {

    private Integer mes;
    private Integer ano;
    private Integer idUnidade;
    private Integer idSegmento;
    private Integer idLocalidade;
    private String motorista;
    private Long quantidade_infracoes;

    public MotoristaQuantidadeInfracoesDTO() {}

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

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public Long getQuantidade_infracoes() {
        return quantidade_infracoes;
    }

    public void setQuantidade_infracoes(Long quantidade_infracoes) {
        this.quantidade_infracoes = quantidade_infracoes;
    }
}