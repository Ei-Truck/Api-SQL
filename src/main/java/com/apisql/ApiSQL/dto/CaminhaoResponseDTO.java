package com.apisql.ApiSQL.dto;

import com.apisql.ApiSQL.model.Caminhao;

public class CaminhaoResponseDTO {

    private Integer id;
    private String placa;
    private String modelo;
    private Integer numeroFrota;
    private int anoFabricacao;
    private SegmentoResponseDTO segmento;

    public CaminhaoResponseDTO() {
    }

    public CaminhaoResponseDTO(Caminhao caminhao) {
        this.id = caminhao.getId();
        this.placa = caminhao.getPlaca();
        this.modelo = caminhao.getModelo();
        this.numeroFrota = caminhao.getNumeroFrota();
        this.anoFabricacao = caminhao.getAnoFabricacao();
        this.segmento = new SegmentoResponseDTO(caminhao.getSegmento());
    }

    public Integer getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getNumeroFrota() {
        return numeroFrota;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public SegmentoResponseDTO getSegmento() {
        return segmento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setNumeroFrota(Integer numeroFrota) {
        this.numeroFrota = numeroFrota;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public void setSegmento(SegmentoResponseDTO segmento) {
        this.segmento = segmento;
    }
}
