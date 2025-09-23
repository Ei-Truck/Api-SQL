package com.apisql.ApiSQL.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Column;


import java.time.LocalDateTime;

@Entity
@Table(name = "tb_infracao")
public class Infracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_viagem")
    private Integer idViagem;

    @Column(name = "id_motorista")
    private Integer idMotorista;

    @Column(name = "dt_hr_evento", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dtHrEvento;

    @Column(name = "id_tipo_infracao")
    private Integer idTipoInfracao;

    private Double latitude;
    private Double longitude;

    @Column(name = "velocidade_kmh")
    private Double velocidadeKmh;

    @Column(name = "transaction_made", length = 20)
    private String transactionMade;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "is_inactive")
    private Boolean isInactive = false;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdViagem() { return idViagem; }
    public void setIdViagem(Integer idViagem) { this.idViagem = idViagem; }

    public Integer getIdMotorista() { return idMotorista; }
    public void setIdMotorista(Integer idMotorista) { this.idMotorista = idMotorista; }

    public LocalDateTime getDtHrEvento() { return dtHrEvento; }
    public void setDtHrEvento(LocalDateTime dtHrEvento) { this.dtHrEvento = dtHrEvento; }

    public Integer getIdTipoInfracao() { return idTipoInfracao; }
    public void setIdTipoInfracao(Integer idTipoInfracao) { this.idTipoInfracao = idTipoInfracao; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getVelocidadeKmh() { return velocidadeKmh; }
    public void setVelocidadeKmh(Double velocidadeKmh) { this.velocidadeKmh = velocidadeKmh; }

    public String getTransactionMade() { return transactionMade; }
    public void setTransactionMade(String transactionMade) { this.transactionMade = transactionMade; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}
