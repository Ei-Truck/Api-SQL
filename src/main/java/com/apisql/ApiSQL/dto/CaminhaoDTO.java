// CaminhaoDTO.java
package com.apisql.ApiSQL.dto;

import com.apisql.ApiSQL.model.Caminhao;

public class CaminhaoDTO {

    private Integer id;
    private String placa;
    private String modelo;
    private Integer numeroFrota;
    private int anoFabricacao;
    private SegmentoDTO segmento;

    public CaminhaoDTO() {
    }

    public CaminhaoDTO(Caminhao caminhao) {
        if (caminhao != null) {
            this.id = caminhao.getId();
            this.placa = caminhao.getPlaca();
            this.modelo = caminhao.getModelo();
            this.numeroFrota = caminhao.getNumeroFrota();
            this.anoFabricacao = caminhao.getAnoFabricacao();
            this.segmento = new SegmentoDTO(caminhao.getSegmento());
        }
    }

    // Getters
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

    public SegmentoDTO getSegmento() {
        return segmento;
    }

    // Setters
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

    public void setSegmento(SegmentoDTO segmento) {
        this.segmento = segmento;
    }
}
