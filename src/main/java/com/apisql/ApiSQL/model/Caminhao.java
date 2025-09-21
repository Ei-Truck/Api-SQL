package com.apisql.ApiSQL.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_caminhao")
public class Caminhao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "chassi", nullable = false, length = 20, unique = true)
    private String chassi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_segmento")
    private Segmento segmento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidade")
    private Unidade unidade;

    @Column(name = "placa", nullable = false, length = 10, unique = true)
    private String placa;

    @Column(name = "modelo", length = 80)
    private String modelo;

    @Column(name = "ano_fabricacao")
    private Integer anoFabricacao;

    @Column(name = "numero_frota", nullable = false)
    private Integer numeroFrota;

    @Column(name = "transaction_made", length = 20)
    private String transactionMade;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_inactive")
    private Boolean isInactive = false;

    // Constructors
    public Caminhao() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getChassi() { return chassi; }
    public void setChassi(String chassi) { this.chassi = chassi; }

    public Segmento getSegmento() { return segmento; }
    public void setSegmento(Segmento segmento) { this.segmento = segmento; }

    public Unidade getUnidade() { return unidade; }
    public void setUnidade(Unidade unidade) { this.unidade = unidade; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Integer getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }

    public Integer getNumeroFrota() { return numeroFrota; }
    public void setNumeroFrota(Integer numeroFrota) { this.numeroFrota = numeroFrota; }

    public String getTransactionMade() { return transactionMade; }
    public void setTransactionMade(String transactionMade) { this.transactionMade = transactionMade; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}