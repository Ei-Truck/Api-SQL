package com.apisql.ApiSQL.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_tipo_infracao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoInfracao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome", nullable = false, length = 50, unique = true)
    private String nome;
    @Column(name = "pontuacao", nullable = false)
    private Integer pontuacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_gravidade")
    private TipoGravidade tipoGravidade;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "is_inactive")
    private Boolean isInactive = false;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getPontuacao() { return pontuacao; }
    public void setPontuacao(Integer pontuacao) { this.pontuacao = pontuacao; }
    public TipoGravidade getTipoGravidade() { return tipoGravidade; }
    public void setTipoGravidade(TipoGravidade tipoGravidade) { this.tipoGravidade = tipoGravidade; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}