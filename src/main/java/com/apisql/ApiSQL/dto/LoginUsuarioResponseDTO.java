package com.apisql.ApiSQL.dto;

import com.apisql.ApiSQL.model.Usuario;

public class LoginUsuarioResponseDTO {
    private String token;
    private String email;
    private String nomeCompleto;
    private String cargo; // novo campo

    public LoginUsuarioResponseDTO(String token, Usuario usuario) {
        this.token = token;
        this.email = usuario.getEmail();
        this.nomeCompleto = usuario.getNomeCompleto();
        this.cargo = usuario.getCargo().getNome(); // pega o nome do cargo
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCargo() {
        return cargo;
    }
}
