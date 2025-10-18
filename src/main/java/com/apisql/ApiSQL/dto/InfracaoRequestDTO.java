package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InfracaoRequestDTO {

    @NotNull(message = "O ID da Viagem é obrigatório")
    @Positive(message = "O ID da Viagem deve ser positivo")
    private Integer idViagem;

    @NotNull(message = "O ID do Motorista é obrigatório")
    @Positive(message = "O ID do Motorista deve ser positivo")
    private Integer idMotorista;

    @NotNull(message = "A data e hora do evento é obrigatória")
    private LocalDateTime dtHrEvento;

    @NotNull(message = "O ID do TipoInfracao é obrigatório")
    @Positive(message = "O ID do TipoInfracao deve ser positivo")
    private Integer idTipoInfracao;

    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal velocidadeKmh;

    public Integer getIdViagem() { return idViagem; }
    public void setIdViagem(Integer idViagem) { this.idViagem = idViagem; }
    public Integer getIdMotorista() { return idMotorista; }
    public void setIdMotorista(Integer idMotorista) { this.idMotorista = idMotorista; }
    public LocalDateTime getDtHrEvento() { return dtHrEvento; }
    public void setDtHrEvento(LocalDateTime dtHrEvento) { this.dtHrEvento = dtHrEvento; }
    public Integer getIdTipoInfracao() { return idTipoInfracao; }
    public void setIdTipoInfracao(Integer idTipoInfracao) { this.idTipoInfracao = idTipoInfracao; }
    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
    public BigDecimal getVelocidadeKmh() { return velocidadeKmh; }
    public void setVelocidadeKmh(BigDecimal velocidadeKmh) { this.velocidadeKmh = velocidadeKmh; }
}