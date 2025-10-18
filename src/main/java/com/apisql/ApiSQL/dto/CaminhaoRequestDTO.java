package com.apisql.ApiSQL.dto;

public class CaminhaoRequestDTO {
    private String chassi;
    private Integer idSegmento;
    private Integer idUnidade;
    private String placa;
    private String modelo;
    private Integer anoFabricacao;
    private Integer numeroFrota;

    public CaminhaoRequestDTO() {}
    public String getChassi() { return chassi; }
    public void setChassi(String chassi) { this.chassi = chassi; }
    public Integer getIdSegmento() { return idSegmento; }
    public void setIdSegmento(Integer idSegmento) { this.idSegmento = idSegmento; }
    public Integer getIdUnidade() { return idUnidade; }
    public void setIdUnidade(Integer idUnidade) { this.idUnidade = idUnidade; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public Integer getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }
    public Integer getNumeroFrota() { return numeroFrota; }
    public void setNumeroFrota(Integer numeroFrota) { this.numeroFrota = numeroFrota; }
}
