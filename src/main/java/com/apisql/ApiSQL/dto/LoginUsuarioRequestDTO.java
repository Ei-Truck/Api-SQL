package com.apisql.ApiSQL.dto;

public class LoginUsuarioRequestDTO {
    private String email;
    private String senha;

    public LoginUsuarioRequestDTO() {}

    public LoginUsuarioRequestDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}