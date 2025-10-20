package com.apisql.ApiSQL.dto.view;

import java.util.Date;

public class VisaoBasicaViagemDTO {

    private Integer idViagem;
    private String placaCaminhao;
    private Date dataInicioViagem;
    private Date dataFimViagem;
    private String kmViagem;
    private String segmento;
    private String unidade;
    private String nomeMotorista;
    private String riscoMotorista;
    private String urlMidiaConcatenada;
    private String tipoGravidade;
    private String tipoInfracao;

    public VisaoBasicaViagemDTO() {
    }

    public Integer getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    public String getPlacaCaminhao() {
        return placaCaminhao;
    }

    public void setPlacaCaminhao(String placaCaminhao) {
        this.placaCaminhao = placaCaminhao;
    }

    public Date getDataInicioViagem() {
        return dataInicioViagem;
    }

    public void setDataInicioViagem(Date dataInicioViagem) {
        this.dataInicioViagem = dataInicioViagem;
    }

    public Date getDataFimViagem() {
        return dataFimViagem;
    }

    public void setDataFimViagem(Date dataFimViagem) {
        this.dataFimViagem = dataFimViagem;
    }

    public String getKmViagem() {
        return kmViagem;
    }

    public void setKmViagem(String kmViagem) {
        this.kmViagem = kmViagem;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getRiscoMotorista() {
        return riscoMotorista;
    }

    public void setRiscoMotorista(String riscoMotorista) {
        this.riscoMotorista = riscoMotorista;
    }

    public String getUrlMidiaConcatenada() {
        return urlMidiaConcatenada;
    }

    public void setUrlMidiaConcatenada(String urlMidiaConcatenada) {
        this.urlMidiaConcatenada = urlMidiaConcatenada;
    }

    public String getTipoGravidade() {
        return tipoGravidade;
    }

    public void setTipoGravidade(String tipoGravidade) {
        this.tipoGravidade = tipoGravidade;
    }

    public String getTipoInfracao() {
        return tipoInfracao;
    }

    public void setTipoInfracao(String tipoInfracao) {
        this.tipoInfracao = tipoInfracao;
    }
}
