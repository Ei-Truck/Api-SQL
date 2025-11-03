package com.apisql.ApiSQL.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class MidiaInfracaoResponseDTO {

    private Integer id;
    private String url;
    private Boolean isConcat;
    private String transactionMade;
    private Boolean isInactive;

    // Assumindo a existência de DTOs
    private ViagemResponseDTO viagem;
    private InfracaoResponseDTO infracao;
    private MotoristaResponseDTO motorista;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")
    private LocalDateTime updatedAt;

    public MidiaInfracaoResponseDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public Boolean getIsConcat() { return isConcat; }
    public void setIsConcat(Boolean isConcat) { this.isConcat = isConcat; }
    public String getTransactionMade() { return transactionMade; }
    public void setTransactionMade(String transactionMade) { this.transactionMade = transactionMade; }
    public Boolean getIsInactive() { return isInactive; }
    public void setIsInactive(Boolean isInactive) { this.isInactive = isInactive; }
    public ViagemResponseDTO getViagem() { return viagem; }
    public void setViagem(ViagemResponseDTO viagem) { this.viagem = viagem; }
    public InfracaoResponseDTO getInfracao() { return infracao; }
    public void setInfracao(InfracaoResponseDTO infracao) { this.infracao = infracao; }
    public MotoristaResponseDTO getMotorista() { return motorista; }
    public void setMotorista(MotoristaResponseDTO motorista) { this.motorista = motorista; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}