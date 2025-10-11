package com.apisql.ApiSQL.dto.view;

import java.time.LocalDateTime;
import java.util.Date;

public class VisaoBasicaViagemDTO {

    private String placaCaminhao;
    private Date dataInicioViagem;
    private Date dataFimViagem;
    private String segmento;
    private String nomeMotorista;
    private String riscoMotorista;
    private Integer idMidiaConcatenada;
    private String urlMidiaConcatenada;
    private Integer idViagem;
    private Integer idSegmento;
    private Integer idMotorista;
    private Integer idTipoGravidade;
    private Integer idTipoRisco;
    private Integer idInfracao;
    private Integer idCaminhao;

    public VisaoBasicaViagemDTO() {
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

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
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

    public Integer getIdMidiaConcatenada() {
        return idMidiaConcatenada;
    }

    public void setIdMidiaConcatenada(Integer idMidiaConcatenada) {
        this.idMidiaConcatenada = idMidiaConcatenada;
    }

    public String getUrlMidiaConcatenada() {
        return urlMidiaConcatenada;
    }

    public void setUrlMidiaConcatenada(String urlMidiaConcatenada) {
        this.urlMidiaConcatenada = urlMidiaConcatenada;
    }

    public Integer getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    public Integer getIdSegmento() {
        return idSegmento;
    }

    public void setIdSegmento(Integer idSegmento) {
        this.idSegmento = idSegmento;
    }

    public Integer getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Integer idMotorista) {
        this.idMotorista = idMotorista;
    }

    public Integer getIdTipoGravidade() {
        return idTipoGravidade;
    }

    public void setIdTipoGravidade(Integer idTipoGravidade) {
        this.idTipoGravidade = idTipoGravidade;
    }

    public Integer getIdTipoRisco() {
        return idTipoRisco;
    }

    public void setIdTipoRisco(Integer idTipoRisco) {
        this.idTipoRisco = idTipoRisco;
    }

    public Integer getIdInfracao() {
        return idInfracao;
    }

    public void setIdInfracao(Integer idInfracao) {
        this.idInfracao = idInfracao;
    }

    public Integer getIdCaminhao() {
        return idCaminhao;
    }

    public void setIdCaminhao(Integer idCaminhao) {
        this.idCaminhao = idCaminhao;
    }
}
