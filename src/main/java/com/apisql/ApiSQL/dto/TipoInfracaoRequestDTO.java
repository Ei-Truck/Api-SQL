package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TipoInfracaoRequestDTO {
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 50, message = "O nome não pode ter mais de 50 caracteres")
    private String nome;

    @NotNull(message = "A pontuação é obrigatória")
    @Min(value = 0, message = "A pontuação não pode ser negativa")
    private Integer pontuacao;

    @NotNull(message = "O ID do TipoGravidade é obrigatório")
    @Positive(message = "O ID do TipoGravidade deve ser positivo")
    private Integer idTipoGravidade;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getPontuacao() { return pontuacao; }
    public void setPontuacao(Integer pontuacao) { this.pontuacao = pontuacao; }
    public Integer getIdTipoGravidade() { return idTipoGravidade; }
    public void setIdTipoGravidade(Integer idTipoGravidade) { this.idTipoGravidade = idTipoGravidade; }
}