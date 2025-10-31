package com.apisql.ApiSQL.dto.view;

import jakarta.persistence.Column;

public class VariacaoMesPassadoPorMesAnoDTO {
    @Column(name = "id_unidade")
    private Integer idUnidade;
    @Column(name = "id_segmento")
    private Integer idSegmento;
    @Column(name = "id_localidade")
    private Integer idLocalidade;
    private Integer mes;
    private Integer ano;
    private Long infracoes_mes_atual;
    private Long infracoes_mes_passado;
    private Double variacao;
    private String ufEstado;

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