package com.apisql.ApiSQL.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_midia_concatenada")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MidiaConcatenada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Foreign Keys são mapeadas como entidades para carregamento Lazy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viagem", nullable = false)
    private Viagem viagem; // Assumindo a existência da entidade Viagem

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_motorista", nullable = false)
    private Motorista motorista; // Assumindo a existência da entidade Motorista

    @Column(name = "url", nullable = false, columnDefinition = "text")
    private String url;

    @Column(name = "transaction_made", length = 20)
    private String transactionMade;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "is_inactive", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isInactive = false;

    // Construtor, Getters e Setters
    public MidiaConcatenada() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Viagem getViagem() { return viagem; }
    public void setViagem(Viagem viagem) { this.viagem = viagem; }
    public Motorista getMotorista() { return motorista; }
    public void setMotorista(Motorista motorista) { this.motorista = motorista; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getTransactionMade() { return transactionMade; }
    public void setTransactionMade(String transactionMade) { this.transactionMade = transactionMade; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}