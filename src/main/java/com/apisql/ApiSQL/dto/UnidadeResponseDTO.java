package com.apisql.ApiSQL.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class UnidadeResponseDTO {
    private Integer id;
    private String nome;
    private SegmentoResponseDTO segmento;
    private LocalidadeResponseDTO localidade;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime updatedAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public SegmentoResponseDTO getSegmento() { return segmento; }
    public void setSegmento(SegmentoResponseDTO segmento) { this.segmento = segmento; }
    public LocalidadeResponseDTO getLocalidade() { return localidade; }
    public void setLocalidade(LocalidadeResponseDTO localidade) { this.localidade = localidade; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}