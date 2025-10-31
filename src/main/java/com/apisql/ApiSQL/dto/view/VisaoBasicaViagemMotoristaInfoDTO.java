package com.apisql.ApiSQL.dto.view;

public class VisaoBasicaViagemMotoristaInfoDTO {
    private Integer idViagem;
    private Integer idMotorista;
    private Integer idSegmento;
    private String segmento;
    private Integer idUnidade;
    private String unidade;
    private Integer idLocalidade;
    private String nomeMotorista;
    private String riscoMotorista;
    private String urlMidiaConcatenada;

    public VisaoBasicaViagemMotoristaInfoDTO() {}

    public Integer getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    public Integer getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Integer idMotorista) {
        this.idMotorista = idMotorista;
    }

    public Integer getIdSegmento() {
        return idSegmento;
    }

    public void setIdSegmento(Integer idSegmento) {
        this.idSegmento = idSegmento;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
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
}
