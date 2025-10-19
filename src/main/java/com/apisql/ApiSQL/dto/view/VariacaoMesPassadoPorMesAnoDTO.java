package com.apisql.ApiSQL.dto.view;

public class VariacaoMesPassadoPorMesAnoDTO {
    private Integer mes;
    private Integer ano;
    private Long infracoes_mes_atual;
    private Long infracoes_mes_passado;
    private Double variacao;

    public VariacaoMesPassadoPorMesAnoDTO() {}

    public Integer getMes() {
        return mes;
    }
    public Integer getAno() {
        return ano;
    }
    public Long getInfracoes_mes_atual() {
        return infracoes_mes_atual;
    }
    public Long getInfracoes_mes_passado() {
        return infracoes_mes_passado;
    }
    public Double getVariacao() {
        return variacao;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }
    public void setAno(Integer ano) {
        this.ano = ano;
    }
    public void setInfracoes_mes_atual(Long infracoes_mes_atual) {
        this.infracoes_mes_atual = infracoes_mes_atual;
    }
    public void setInfracoes_mes_passado(Long infracoes_mes_passado) {
        this.infracoes_mes_passado = infracoes_mes_passado;
    }
    public void setVariacao(Double variacao) {
        this.variacao = variacao;
    }
}