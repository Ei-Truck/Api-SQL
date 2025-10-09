package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ViagemRequestDTO {
    @NotNull(message = "ID do Caminhão é obrigatório")
    private Integer idCaminhao;

    @NotNull(message = "ID do Usuário é obrigatório")
    private Integer idUsuario;

    @NotNull(message = "ID da Localidade de Origem é obrigatório")
    private Integer idOrigem;

    @NotNull(message = "ID da Localidade de Destino é obrigatório")
    private Integer idDestino;

    private LocalDateTime dtHrInicio;
    private LocalDateTime dtHrFim;
    private String kmViagem;
    private Boolean wasAnalyzed;

    public Integer getIdCaminhao() { return idCaminhao; }
    public void setIdCaminhao(Integer idCaminhao) { this.idCaminhao = idCaminhao; }
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    public Integer getIdOrigem() { return idOrigem; }
    public void setIdOrigem(Integer idOrigem) { this.idOrigem = idOrigem; }
    public Integer getIdDestino() { return idDestino; }
    public void setIdDestino(Integer idDestino) { this.idDestino = idDestino; }
    public LocalDateTime getDtHrInicio() { return dtHrInicio; }
    public void setDtHrInicio(LocalDateTime dtHrInicio) { this.dtHrInicio = dtHrInicio; }
    public LocalDateTime getDtHrFim() { return dtHrFim; }
    public void setDtHrFim(LocalDateTime dtHrFim) { this.dtHrFim = dtHrFim; }
    public String getKmViagem() { return kmViagem; }
    public void setKmViagem(String kmViagem) { this.kmViagem = kmViagem; }
    public Boolean getWasAnalyzed() { return wasAnalyzed; }
    public void setWasAnalyzed(Boolean wasAnalyzed) { this.wasAnalyzed = wasAnalyzed; }
}