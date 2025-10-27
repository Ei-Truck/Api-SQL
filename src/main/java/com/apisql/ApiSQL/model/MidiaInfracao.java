package com.apisql.ApiSQL.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_midia_infracao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MidiaInfracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viagem", nullable = false)
    private Viagem viagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_infracao", nullable = false)
    private Infracao infracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_motorista", nullable = false)
    private Motorista motorista;

    @Column(name = "url", nullable = false, columnDefinition = "text")
    private String url;

    @Column(name = "is_concat", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isConcat = false;

    @Column(name = "transaction_made", length = 20)
    private String transactionMade;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    @Column(name = "is_inactive", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isInactive = false;

    public MidiaInfracao() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Viagem getViagem() { return viagem; }
    public void setViagem(Viagem viagem) { this.viagem = viagem; }
    public Infracao getInfracao() { return infracao; }
    public void setInfracao(Infracao infracao) { this.infracao = infracao; }
    public Motorista getMotorista() { return motorista; }
    public void setMotorista(Motorista motorista) { this.motorista = motorista; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public Boolean getIsConcat() { return isConcat; }
    public void setIsConcat(Boolean isConcat) { this.isConcat = isConcat; }
    public String getTransactionMade() { return transactionMade; }
    public void setTransactionMade(String transactionMade) { this.transactionMade = transactionMade; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}