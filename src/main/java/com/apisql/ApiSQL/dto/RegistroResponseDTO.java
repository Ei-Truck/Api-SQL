package com.apisql.ApiSQL.dto;

import java.time.LocalDateTime;

public class RegistroResponseDTO {

    private Integer id;
    private Integer idViagem;
    private Integer idMotorista;
    private String tratativa;
    private LocalDateTime dtHrRegistro;
    private String transactionMade;
    private LocalDateTime updatedAt;
    private Boolean isInactive;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getIdViagem() { return idViagem; }
    public void setIdViagem(Integer idViagem) { this.idViagem = idViagem; }
    public Integer getIdMotorista() { return idMotorista; }
    public void setIdMotorista(Integer idMotorista) { this.idMotorista = idMotorista; }
    public String getTratativa() { return tratativa; }
    public void setTratativa(String tratativa) { this.tratativa = tratativa; }
    public LocalDateTime getDtHrRegistro() { return dtHrRegistro; }
    public void setDtHrRegistro(LocalDateTime dtHrRegistro) { this.dtHrRegistro = dtHrRegistro; }
    public String getTransactionMade() { return transactionMade; }
    public void setTransactionMade(String transactionMade) { this.transactionMade = transactionMade; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
}