package com.apisql.ApiSQL.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_segmento")
public class Segmento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 40, unique = true)
    private String nome;

    @Column(name = "transaction_made", length = 20)
    private String transactionMade;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_inactive")
    private Boolean isInactive = false;

    // Constructors
    public Segmento() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTransactionMade() { return transactionMade; }
    public void setTransactionMade(String transactionMade) { this.transactionMade = transactionMade; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}