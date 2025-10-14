package com.apisql.ApiSQL.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class CaminhaoResponseDTO {

    private Integer id;
    private String chassi;
    private String placa;
    private String modelo;
    private Integer anoFabricacao;
    private Integer numeroFrota;

    private SegmentoResponseDTO segmento;
    private UnidadeResponseDTO unidade;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime updatedAt;
    private Boolean isInactive;

    public CaminhaoResponseDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getChassi() { return chassi; }
    public void setChassi(String chassi) { this.chassi = chassi; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public Integer getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }
    public Integer getNumeroFrota() { return numeroFrota; }
    public void setNumeroFrota(Integer numeroFrota) { this.numeroFrota = numeroFrota; }

    public SegmentoResponseDTO getSegmento() { return segmento; }
    public void setSegmento(SegmentoResponseDTO segmento) { this.segmento = segmento; }
    public UnidadeResponseDTO getUnidade() { return unidade; }
    public void setUnidade(UnidadeResponseDTO unidade) { this.unidade = unidade; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}