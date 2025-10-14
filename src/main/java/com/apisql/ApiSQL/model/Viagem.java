package com.apisql.ApiSQL.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_viagem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_caminhao", nullable = false)
    private Caminhao caminhao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_origem")
    private Localidade origem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destino")
    private Localidade destino;

    @Column(name = "dt_hr_inicio")
    private LocalDateTime dtHrInicio;

    @Column(name = "dt_hr_fim")
    private LocalDateTime dtHrFim;

    @Column(name = "km_viagem")
    private String kmViagem;

    @Column(name = "was_analyzed")
    private Boolean wasAnalyzed = false;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_inactive")
    private Boolean isInactive = false;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Caminhao getCaminhao() { return caminhao; }
    public void setCaminhao(Caminhao caminhao) { this.caminhao = caminhao; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Localidade getOrigem() { return origem; }
    public void setOrigem(Localidade origem) { this.origem = origem; }
    public Localidade getDestino() { return destino; }
    public void setDestino(Localidade destino) { this.destino = destino; }
    public LocalDateTime getDtHrInicio() { return dtHrInicio; }
    public void setDtHrInicio(LocalDateTime dtHrInicio) { this.dtHrInicio = dtHrInicio; }
    public LocalDateTime getDtHrFim() { return dtHrFim; }
    public void setDtHrFim(LocalDateTime dtHrFim) { this.dtHrFim = dtHrFim; }
    public String getKmViagem() { return kmViagem; }
    public void setKmViagem(String kmViagem) { this.kmViagem = kmViagem; }
    public Boolean getWasAnalyzed() { return wasAnalyzed; }
    public void setWasAnalyzed(Boolean wasAnalyzed) { this.wasAnalyzed = wasAnalyzed; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}