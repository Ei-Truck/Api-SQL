package com.apisql.ApiSQL.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lg_login_usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LoginUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "dt_hr_login")
    private LocalDateTime dtHrLogin;

    public LoginUsuario(Integer id) {

    }

    public LoginUsuario() {

    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public LocalDateTime getDtHrLogin() { return dtHrLogin; }
    public void setDtHrLogin(LocalDateTime dtHrLogin) { this.dtHrLogin = dtHrLogin; }
}