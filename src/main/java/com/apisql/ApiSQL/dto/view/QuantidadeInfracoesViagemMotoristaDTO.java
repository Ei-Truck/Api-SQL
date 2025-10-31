package com.apisql.ApiSQL.dto.view;

public class QuantidadeInfracoesViagemMotoristaDTO {
    private Integer idMotorista;
    private Integer idViagem;
    private Long quantidadeInfracoes;

    public QuantidadeInfracoesViagemMotoristaDTO() {}

    public Integer getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Integer idMotorista) {
        this.idMotorista = idMotorista;
    }

    public Integer getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    public Long getQuantidadeInfracoes() {
        return quantidadeInfracoes;
    }

    public void setQuantidadeInfracoes(Long quantidadeInfracoes) {
        this.quantidadeInfracoes = quantidadeInfracoes;
    }
}