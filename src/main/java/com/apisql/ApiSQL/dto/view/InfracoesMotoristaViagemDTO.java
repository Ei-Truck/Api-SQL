package com.apisql.ApiSQL.dto.view;


public class InfracoesMotoristaViagemDTO {

    private Integer idMotorista;
    private Integer idViagem;
    private Integer idUnidade;
    private Integer idLocalidade;
    private String nomeMotorista;
    private String urlMidiaConcatenada;
    private String riscoMotorista;
    private Long quantidadeInfracao;

    public InfracoesMotoristaViagemDTO() {}


    public Integer getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Integer idMotorista) {
        this.idMotorista = idMotorista;
    }

    public Integer getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
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

    public String getUrlMidiaConcatenada() {
        return urlMidiaConcatenada;
    }

    public void setUrlMidiaConcatenada(String urlMidiaConcatenada) {
        this.urlMidiaConcatenada = urlMidiaConcatenada;
    }

    public String getRiscoMotorista() {
        return riscoMotorista;
    }

    public void setRiscoMotorista(String riscoMotorista) {
        this.riscoMotorista = riscoMotorista;
    }

    public Long getQuantidadeInfracao() {
        return quantidadeInfracao;
    }

    public void setQuantidadeInfracao(Long quantidadeInfracao) {
        this.quantidadeInfracao = quantidadeInfracao;
    }
}