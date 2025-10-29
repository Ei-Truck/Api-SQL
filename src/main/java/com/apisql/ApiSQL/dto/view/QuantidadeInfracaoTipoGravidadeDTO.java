package com.apisql.ApiSQL.dto.view;


public class QuantidadeInfracaoTipoGravidadeDTO {

    private Integer idViagem;
    private Integer idMotorista;
    private Integer idUnidade;
    private Integer idLocalidade;

    private Long tipoLeve;
    private Long tipoMedia;
    private Long tipoGrave;
    private Long tipoGravissima;

    public QuantidadeInfracaoTipoGravidadeDTO() {}


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

    public Long getTipoLeve() {
        return tipoLeve;
    }

    public void setTipoLeve(Long tipoLeve) {
        this.tipoLeve = tipoLeve;
    }

    public Long getTipoMedia() {
        return tipoMedia;
    }

    public void setTipoMedia(Long tipoMedia) {
        this.tipoMedia = tipoMedia;
    }

    public Long getTipoGrave() {
        return tipoGrave;
    }

    public void setTipoGrave(Long tipoGrave) {
        this.tipoGrave = tipoGrave;
    }

    public Long getTipoGravissima() {
        return tipoGravissima;
    }

    public void setTipoGravissima(Long tipoGravissima) {
        this.tipoGravissima = tipoGravissima;
    }
}