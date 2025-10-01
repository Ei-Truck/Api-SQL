package com.apisql.ApiSQL.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "lg_login_usuario")
public class LoginUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "dt_hr_login")
    private LocalDateTime dtHrLogin = LocalDateTime.now();

    public LoginUsuario(Integer id, Integer idUsuario, LocalDateTime dtHrLogin) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.dtHrLogin = dtHrLogin;
    }
    public LoginUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
        this.dtHrLogin = LocalDateTime.now();
    }


    public LoginUsuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getDtHrLogin() {
        return dtHrLogin;
    }

    public void setDtHrLogin(LocalDateTime dtHrLogin) {
        this.dtHrLogin = dtHrLogin;
    }
}