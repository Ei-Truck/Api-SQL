package com.apisql.ApiSQL.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tipo_gravidade")
public class TipoGravidade {

    @Id
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @Column(name = "transaction_made", length = 20)
    private String transactionMade;

    private LocalDateTime updatedAt = LocalDateTime.now();

    private Boolean isInactive = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getIsInactive() {
        return isInactive;
    }

    public void setIsInactive(Boolean inactive) {
        isInactive = inactive;
    }
}
