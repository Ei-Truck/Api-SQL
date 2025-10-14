package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UnidadeRequestDTO {
    @NotNull(message = "O ID do Segmento é obrigatório")
    @Positive(message = "O ID do Segmento deve ser positivo")
    private Integer idSegmento;

    @NotBlank(message = "O nome da Unidade é obrigatório")
    @Size(max = 100, message = "O nome não pode ter mais de 100 caracteres")
    private String nome;

    @NotNull(message = "O ID da Localidade é obrigatório")
    @Positive(message = "O ID da Localidade deve ser positivo")
    private Integer idLocalidade;

    public Integer getIdSegmento() { return idSegmento; }
    public void setIdSegmento(Integer idSegmento) { this.idSegmento = idSegmento; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getIdLocalidade() { return idLocalidade; }
    public void setIdLocalidade(Integer idLocalidade) { this.idLocalidade = idLocalidade; }
}