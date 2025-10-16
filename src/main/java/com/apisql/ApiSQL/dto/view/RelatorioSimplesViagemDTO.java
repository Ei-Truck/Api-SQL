package com.apisql.ApiSQL.dto.view;

import java.util.Date;

public class RelatorioSimplesViagemDTO {
    private Integer idViagem;
    private String placaCaminhao;
    private Date dataInicioViagem;
    private String nomeMotorista;
    private String kmViagem;
    private Long pontuacaoTotal;
    private Boolean wasAnalysed;


    public RelatorioSimplesViagemDTO() {}

    public Integer getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
    }

    public String getPlacaCaminhao() {
        return placaCaminhao;
    }

    public void setPlacaCaminhao(String placaCaminhao) {
        this.placaCaminhao = placaCaminhao;
    }

    public Date getDataInicioViagem() {
        return dataInicioViagem;
    }

    public void setDataInicioViagem(Date dataInicioViagem) {
        this.dataInicioViagem = dataInicioViagem;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public String getKmViagem() {
        return kmViagem;
    }

    public void setKmViagem(String kmViagem) {
        this.kmViagem = kmViagem;
    }

    public Long getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void setPontuacaoTotal(Long pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }

    public Boolean getWasAnalysed() {
        return wasAnalysed;
    }

    public void setWasAnalysed(Boolean wasAnalysed) {
        this.wasAnalysed = wasAnalysed;
    }
}
