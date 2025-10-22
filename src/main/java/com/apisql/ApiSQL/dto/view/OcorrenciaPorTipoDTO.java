package com.apisql.ApiSQL.dto.view;

import java.math.BigDecimal;

public class OcorrenciaPorTipoDTO {
    private String tipo_infracao;
    private Long total_ocorrencias;
    private BigDecimal porcentagem_do_total;
    private BigDecimal mes;
    private BigDecimal ano;

    public OcorrenciaPorTipoDTO() {

    }

    public String getTipo_infracao() {
        return tipo_infracao;
    }

    public void setTipo_infracao(String tipo_infracao) {
        this.tipo_infracao = tipo_infracao;
    }

    public Long getTotal_ocorrencias() {
        return total_ocorrencias;
    }

    public void setTotal_ocorrencias(Long total_ocorrencias) {
        this.total_ocorrencias = total_ocorrencias;
    }

    public BigDecimal getPorcentagem_do_total() {
        return porcentagem_do_total;
    }

    public void setPorcentagem_do_total(BigDecimal porcentagem_do_total) {
        this.porcentagem_do_total = porcentagem_do_total;
    }

    public BigDecimal getMes() {
        return mes;
    }

    public void setMes(BigDecimal mes) {
        this.mes = mes;
    }

    public BigDecimal getAno() {
        return ano;
    }

    public void setAno(BigDecimal ano) {
        this.ano = ano;
    }
}
