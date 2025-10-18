package com.apisql.ApiSQL.dto.view;

public class MotoristaPontuacaoMensalDTO {
    private Integer ranking;
    private String motorista;
    private String unidade;
    private String segmento;
    private Integer pontuacao;

    public MotoristaPontuacaoMensalDTO() {

    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }
}
