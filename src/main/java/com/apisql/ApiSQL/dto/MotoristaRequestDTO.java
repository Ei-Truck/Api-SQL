package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.*;

public class MotoristaRequestDTO {
    @NotBlank(message = "CPF é obrigatório")
    @Size(max = 15)
    private String cpf;

    @NotNull(message = "ID da Unidade é obrigatório")
    private Integer idUnidade;

    @NotBlank(message = "CNH é obrigatória")
    @Size(max = 15)
    private String cnh;

    @NotBlank(message = "Nome Completo é obrigatório")
    @Size(max = 150)
    private String nomeCompleto;

    @NotBlank(message = "Telefone é obrigatório")
    @Size(max = 15)
    private String telefone;

    @Email(message = "E-mail inválido")
    @Size(max = 150)
    private String emailEmpresa;

    @NotNull(message = "ID do Tipo de Risco é obrigatório")
    private Integer idTipoRisco;

    private String urlFoto;

    // Getters e Setters (omitidos para brevidade, mas devem existir)
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public Integer getIdUnidade() { return idUnidade; }
    public void setIdUnidade(Integer idUnidade) { this.idUnidade = idUnidade; }
    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmailEmpresa() { return emailEmpresa; }
    public void setEmailEmpresa(String emailEmpresa) { this.emailEmpresa = emailEmpresa; }
    public Integer getIdTipoRisco() { return idTipoRisco; }
    public void setIdTipoRisco(Integer idTipoRisco) { this.idTipoRisco = idTipoRisco; }
    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }
}