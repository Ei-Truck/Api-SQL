package com.apisql.ApiSQL.dto.view;

public class MotoristaQuantidadeInfracoesDTO {
    private String motorista;
    private Long quantidade_infracoes;
    private Integer mes;
    private Integer ano;

    public MotoristaQuantidadeInfracoesDTO() {}

    public String getMotorista() {
        return motorista;
    }
    public Long getQuantidade_infracoes() {
        return quantidade_infracoes;
    }
    public Integer getMes() {
        return mes;
    }
    public Integer getAno() {
        return ano;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }
    public void setQuantidade_infracoes(Long quantidade_infracoes) {
        this.quantidade_infracoes = quantidade_infracoes;
    }
    public void setMes(Integer mes) {
        this.mes = mes;
    }
    public void setAno(Integer ano) {
        this.ano = ano;
    }
}