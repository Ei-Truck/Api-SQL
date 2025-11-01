package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RegistroRequestDTO {

    @NotNull
    private Integer idViagem;

    private Integer idMotorista;

    private String tratativa;

    private String transactionMade;

    public Integer getIdViagem() { return idViagem; }
    public void setIdViagem(Integer idViagem) { this.idViagem = idViagem; }
    public Integer getIdMotorista() { return idMotorista; }
    public void setIdMotorista(Integer idMotorista) { this.idMotorista = idMotorista; }
    public String getTratativa() { return tratativa; }
    public void setTratativa(String tratativa) { this.tratativa = tratativa; }
    public String getTransactionMade() { return transactionMade; }
    public void setTransactionMade(String transactionMade) { this.transactionMade = transactionMade; }
}