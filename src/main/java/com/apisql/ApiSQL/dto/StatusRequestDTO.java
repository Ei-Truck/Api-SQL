package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StatusRequestDTO {
    @NotBlank(message = "O nome do status é obrigatório")
    @Size(max = 20, message = "O nome não pode ter mais de 20 caracteres")
    private String nome;
    private Boolean isInactive;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}