package com.apisql.ApiSQL.dto;

import java.time.LocalDateTime;

public class LoginUsuarioRequestDTO {
    private Integer idUsuario;

    public LoginUsuarioRequestDTO() {}

    public LoginUsuarioRequestDTO(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}