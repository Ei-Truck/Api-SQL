package com.apisql.ApiSQL.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MidiaConcatenadaRequestDTO {

    @NotNull(message = "O ID da Viagem é obrigatório.")
    private Integer idViagem;

    @NotNull(message = "O ID do Motorista é obrigatório.")
    private Integer idMotorista;

    @NotBlank(message = "A URL da mídia é obrigatória.")
    private String url;

    private String transactionMade;

    // Construtor, Getters e Setters
    public MidiaConcatenadaRequestDTO() {}

    public Integer getIdViagem() { return idViagem; }
    public void setIdViagem(Integer idViagem) { this.idViagem = idViagem; }
    public Integer getIdMotorista() { return idMotorista; }
    public void setIdMotorista(Integer idMotorista) { this.idMotorista = idMotorista; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getTransactionMade() { return transactionMade; }
    public void setTransactionMade(String transactionMade) { this.transactionMade = transactionMade; }
}