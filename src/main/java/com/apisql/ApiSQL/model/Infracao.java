package com.apisql.ApiSQL.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_infracao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Infracao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viagem")
    private Viagem viagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_motorista")
    private Motorista motorista;

    @Column(name = "dt_hr_evento")
    private LocalDateTime dtHrEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_infracao")
    private TipoInfracao tipoInfracao;

    @Column(name = "latitude", precision = 9, scale = 7)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 9, scale = 7)
    private BigDecimal longitude;

    @Column(name = "velocidade_kmh", precision = 5, scale = 2)
    private BigDecimal velocidadeKmh;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "is_inactive")
    private Boolean isInactive = false;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Viagem getViagem() { return viagem; }
    public void setViagem(Viagem viagem) { this.viagem = viagem; }
    public Motorista getMotorista() { return motorista; }
    public void setMotorista(Motorista motorista) { this.motorista = motorista; }
    public LocalDateTime getDtHrEvento() { return dtHrEvento; }
    public void setDtHrEvento(LocalDateTime dtHrEvento) { this.dtHrEvento = dtHrEvento; }
    public TipoInfracao getTipoInfracao() { return tipoInfracao; }
    public void setTipoInfracao(TipoInfracao tipoInfracao) { this.tipoInfracao = tipoInfracao; }
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