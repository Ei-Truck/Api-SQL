package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioSenhaPatchDTO {

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
