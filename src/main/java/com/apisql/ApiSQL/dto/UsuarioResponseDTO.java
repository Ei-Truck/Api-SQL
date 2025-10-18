package com.apisql.ApiSQL.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class UsuarioResponseDTO {
    private Integer id;
    private String cpf;
    private UnidadeResponseDTO unidade;
    private String nomeCompleto;
    private String telefone;
    private String email;
    private String urlFoto;
    private CargoResponseDTO cargo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtContratacao;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime updatedAt;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public UnidadeResponseDTO getUnidade() { return unidade; }
    public void setUnidade(UnidadeResponseDTO unidade) { this.unidade = unidade; }
    public LocalDate getDtContratacao() { return dtContratacao; }
    public void setDtContratacao(LocalDate dtContratacao) { this.dtContratacao = dtContratacao; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }
    public CargoResponseDTO getCargo() { return cargo; }
    public void setCargo(CargoResponseDTO cargo) { this.cargo = cargo; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}