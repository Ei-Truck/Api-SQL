package com.apisql.ApiSQL.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class TipoInfracaoResponseDTO {
    private Integer id;
    private String nome;
    private Integer pontuacao;
    private TipoGravidadeResponseDTO tipoGravidade;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime updatedAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getPontuacao() { return pontuacao; }
    public void setPontuacao(Integer pontuacao) { this.pontuacao = pontuacao; }
    public TipoGravidadeResponseDTO getTipoGravidade() { return tipoGravidade; }
    public void setTipoGravidade(TipoGravidadeResponseDTO tipoGravidade) { this.tipoGravidade = tipoGravidade; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}