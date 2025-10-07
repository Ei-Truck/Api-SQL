package com.apisql.ApiSQL.dto;

import com.apisql.ApiSQL.model.Viagem;
import java.time.LocalDateTime;

public class ViagemResponseDTO {

    private Integer id;
    private LocalDateTime dtHrInicio;
    private LocalDateTime dtHrFim;
    private String kmViagem;
    private CaminhaoResponseDTO caminhao;
    private Boolean wasAnalyzed = false;

    public ViagemResponseDTO() {
    }

    public ViagemResponseDTO(Viagem viagem) {
        this.id = viagem.getId();
        this.dtHrInicio = viagem.getDtHrInicio();
        this.dtHrFim = viagem.getDtHrFim();
        this.kmViagem = viagem.getKmViagem();
        this.caminhao = new CaminhaoResponseDTO(viagem.getCaminhao());
        this.wasAnalyzed = viagem.getWasAnalyzed();
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public LocalDateTime getDtHrInicio() {
        return dtHrInicio;
    }

    public LocalDateTime getDtHrFim() {
        return dtHrFim;
    }

    public String getKmViagem() {
        return kmViagem;
    }

    public CaminhaoResponseDTO getCaminhao() {
        return caminhao;
    }
    public Boolean getWasAnalyzed() {
        return wasAnalyzed;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setDtHrInicio(LocalDateTime dtHrInicio) {
        this.dtHrInicio = dtHrInicio;
    }

    public void setDtHrFim(LocalDateTime dtHrFim) {
        this.dtHrFim = dtHrFim;
    }

    public void setKmViagem(String kmViagem) {
        this.kmViagem = kmViagem;
    }

    public void setCaminhao(CaminhaoResponseDTO caminhao) {
        this.caminhao = caminhao;
    }
    public void setWasAnalyzed(Boolean wasAnalyzed) {
        this.wasAnalyzed = wasAnalyzed;
    }
}
