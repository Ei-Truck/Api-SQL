package com.apisql.ApiSQL.dto.view;

public class MotoristaPontuacaoMensalDTO {
    private Integer ranking_pontuacao;
    private String motorista;
    private Integer id_unidade;
    private String unidade;
    private Integer id_segmento;
    private String segmento;
    private Double pontuacao_ultimo_mes;

    public MotoristaPontuacaoMensalDTO() {}

    public Integer getRanking_pontuacao() {
        return ranking_pontuacao;
    }
    public String getMotorista() {
        return motorista;
    }
    public Integer getId_unidade() {
        return id_unidade;
    }
    public String getUnidade() {
        return unidade;
    }
    public Integer getId_segmento() {
        return id_segmento;
    }
    public String getSegmento() {
        return segmento;
    }
    public Double getPontuacao_ultimo_mes() {
        return pontuacao_ultimo_mes;
    }

    public void setRanking_pontuacao(Integer ranking_pontuacao) {
        this.ranking_pontuacao = ranking_pontuacao;
    }
    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }
    public void setId_unidade(Integer id_unidade) {
        this.id_unidade = id_unidade;
    }
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
    public void setId_segmento(Integer id_segmento) {
        this.id_segmento = id_segmento;
    }
    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }
    public void setPontuacao_ultimo_mes(Double pontuacao_ultimo_mes) {
        this.pontuacao_ultimo_mes = pontuacao_ultimo_mes;
    }
}