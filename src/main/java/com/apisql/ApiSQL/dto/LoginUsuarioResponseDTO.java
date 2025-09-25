package com.apisql.ApiSQL.dto;

import java.time.LocalDateTime;

public class LoginUsuarioResponseDTO {
    private Integer id;
    private Integer idUsuario;
    private LocalDateTime dtHrLogin;

    public LoginUsuarioResponseDTO() {}

    public LoginUsuarioResponseDTO(Integer id, Integer idUsuario, LocalDateTime dtHrLogin) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.dtHrLogin = dtHrLogin;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public LocalDateTime getDtHrLogin() {
        return dtHrLogin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setDtHrLogin(LocalDateTime dtHrLogin) {
        this.dtHrLogin = dtHrLogin;
    }
}