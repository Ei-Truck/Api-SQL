package com.apisql.ApiSQL.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 15, nullable = false, unique = true)
    private String cpf;

    @Column(name = "id_unidade")
    private Integer idUnidade;

    @Column(name = "id_perfil")
    private Integer idPerfil;

    @Column(name = "dt_contratacao")
    private LocalDate dtContratacao;

    @Column(name = "nome_completo", length = 150, nullable = false)
    private String nomeCompleto;

    @Column(length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "hash_senha", length = 100, nullable = false)
    private String hashSenha;

    @Column(name = "url_foto", length = 255)
    private String urlFoto = "Sem foto";

    @Column(name = "id_status")
    private Integer idStatus;

    @Column(name = "id_cargo", nullable = false)
    private Integer idCargo;

    @Column(name = "isinactive")
    private Boolean isInactive = false;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public Integer getIdUnidade() { return idUnidade; }
    public void setIdUnidade(Integer idUnidade) { this.idUnidade = idUnidade; }

    public Integer getIdPerfil() { return idPerfil; }
    public void setIdPerfil(Integer idPerfil) { this.idPerfil = idPerfil; }

    public LocalDate getDtContratacao() { return dtContratacao; }
    public void setDtContratacao(LocalDate dtContratacao) { this.dtContratacao = dtContratacao; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getHashSenha() { return hashSenha; }
    public void setHashSenha(String hashSenha) { this.hashSenha = hashSenha; }

    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }

    public Integer getIdStatus() { return idStatus; }
    public void setIdStatus(Integer idStatus) { this.idStatus = idStatus; }

    public Integer getIdCargo() { return idCargo; }
    public void setIdCargo(Integer idCargo) { this.idCargo = idCargo; }

    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}