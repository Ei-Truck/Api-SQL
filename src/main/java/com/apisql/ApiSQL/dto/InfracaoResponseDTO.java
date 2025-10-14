package com.apisql.ApiSQL.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class InfracaoResponseDTO {
    private Integer id;
    private ViagemResponseDTO viagem;
    private MotoristaResponseDTO motorista;
    private LocalDateTime dtHrEvento;
    private TipoInfracaoResponseDTO tipoInfracao;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal velocidadeKmh;
    private LocalDateTime updatedAt;
    private Boolean isInactive;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public ViagemResponseDTO getViagem() { return viagem; }
    public void setViagem(ViagemResponseDTO viagem) { this.viagem = viagem; }
    public MotoristaResponseDTO getMotorista() { return motorista; }
    public void setMotorista(MotoristaResponseDTO motorista) { this.motorista = motorista; }
    public LocalDateTime getDtHrEvento() { return dtHrEvento; }
    public void setDtHrEvento(LocalDateTime dtHrEvento) { this.dtHrEvento = dtHrEvento; }
    public TipoInfracaoResponseDTO getTipoInfracao() { return tipoInfracao; }
    public void setTipoInfracao(TipoInfracaoResponseDTO tipoInfracao) { this.tipoInfracao = tipoInfracao; }
    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
    public BigDecimal getVelocidadeKmh() { return velocidadeKmh; }
    public void setVelocidadeKmh(BigDecimal velocidadeKmh) { this.velocidadeKmh = velocidadeKmh; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}