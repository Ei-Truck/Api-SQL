package com.apisql.ApiSQL.dto;

import java.time.LocalDateTime;

public class ViagemResponseDTO {
    private Integer id;
    private CaminhaoResponseDTO caminhao;
    private UsuarioResponseDTO usuario;
    private LocalidadeResponseDTO origem;
    private LocalidadeResponseDTO destino;
    private LocalDateTime dtHrInicio;
    private LocalDateTime dtHrFim;
    private String kmViagem;
    private Boolean wasAnalyzed;
    private LocalDateTime updatedAt;
    private Boolean isInactive;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public CaminhaoResponseDTO getCaminhao() { return caminhao; }
    public void setCaminhao(CaminhaoResponseDTO caminhao) { this.caminhao = caminhao; }
    public UsuarioResponseDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioResponseDTO usuario) { this.usuario = usuario; }
    public LocalidadeResponseDTO getOrigem() { return origem; }
    public void setOrigem(LocalidadeResponseDTO origem) { this.origem = origem; }
    public LocalidadeResponseDTO getDestino() { return destino; }
    public void setDestino(LocalidadeResponseDTO destino) { this.destino = destino; }
    public LocalDateTime getDtHrInicio() { return dtHrInicio; }
    public void setDtHrInicio(LocalDateTime dtHrInicio) { this.dtHrInicio = dtHrInicio; }
    public LocalDateTime getDtHrFim() { return dtHrFim; }
    public void setDtHrFim(LocalDateTime dtHrFim) { this.dtHrFim = dtHrFim; }
    public String getKmViagem() { return kmViagem; }
    public void setKmViagem(String kmViagem) { this.kmViagem = kmViagem; }
    public Boolean getWasAnalyzed() { return wasAnalyzed; }
    public void setWasAnalyzed(Boolean wasAnalyzed) { this.wasAnalyzed = wasAnalyzed; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}