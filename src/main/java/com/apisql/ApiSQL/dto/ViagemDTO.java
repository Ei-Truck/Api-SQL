// ViagemDTO.java
package com.apisql.ApiSQL.dto;

import com.apisql.ApiSQL.model.Viagem;
import java.time.LocalDateTime;

public class ViagemDTO {

    private Integer id;
    private LocalDateTime dtHrInicio;
    private LocalDateTime dtHrFim;
    private String kmViagem;
    private CaminhaoDTO caminhao;

    public ViagemDTO() {
    }

    public ViagemDTO(Viagem viagem) {
        if (viagem != null) {
            this.id = viagem.getId();
            this.dtHrInicio = viagem.getDtHrInicio();
            this.dtHrFim = viagem.getDtHrFim();
            this.kmViagem = viagem.getKmViagem();
            this.caminhao = new CaminhaoDTO(viagem.getCaminhao());
        }
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

    public CaminhaoDTO getCaminhao() {
        return caminhao;
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

    public void setCaminhao(CaminhaoDTO caminhao) {
        this.caminhao = caminhao;
    }
}
