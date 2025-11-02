package com.apisql.ApiSQL.dto;

public class UsuarioSenhaResponseDTO {
    private Integer id;
    private String email;
    private String codigo;

    public UsuarioSenhaResponseDTO(Integer id, String email, String codigo) {
        this.id = id;
        this.email = email;
        this.codigo = codigo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
