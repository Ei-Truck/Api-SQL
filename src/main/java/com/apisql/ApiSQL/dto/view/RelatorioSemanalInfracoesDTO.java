package com.apisql.ApiSQL.dto.view;

public class RelatorioSemanalInfracoesDTO {
    private String diasemana;
    private Integer total_infracoes;

    public RelatorioSemanalInfracoesDTO() {}

    public Integer getTotal_infracoes() {
        return total_infracoes;
    }
    public String getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(String diasemana) {}
    public void setTotal_infracoes(Integer total_infracoes) {}
}
