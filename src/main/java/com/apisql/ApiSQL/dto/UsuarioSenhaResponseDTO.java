package com.apisql.ApiSQL.dto;

public class UsuarioSenhaResponseDTO {
    private Integer id;
    private String telefone;
    private String codigo;

    public UsuarioSenhaResponseDTO(Integer id, String telefone, String codigo) {
        this.id = id;
        this.telefone = telefone;
        this.codigo = codigo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
