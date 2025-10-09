package com.apisql.ApiSQL.dto;

import java.time.LocalDateTime;

public class MotoristaResponseDTO {
    private Integer id;
    private String cpf;
    private UnidadeResponseDTO unidade;
    private String cnh;
    private String nomeCompleto;
    private String telefone;
    private String emailEmpresa;
    private TipoRiscoResponseDTO tipoRisco;
    private String urlFoto;
    private LocalDateTime updatedAt;
    private Boolean isInactive;


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public UnidadeResponseDTO getUnidade() { return unidade; }
    public void setUnidade(UnidadeResponseDTO unidade) { this.unidade = unidade; }
    public String getCnh() { return cnh; }
    public void setCnh(String cnh) { this.cnh = cnh; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmailEmpresa() { return emailEmpresa; }
    public void setEmailEmpresa(String emailEmpresa) { this.emailEmpresa = emailEmpresa; }
    public TipoRiscoResponseDTO getTipoRisco() { return tipoRisco; }
    public void setTipoRisco(TipoRiscoResponseDTO tipoRisco) { this.tipoRisco = tipoRisco; }
    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}