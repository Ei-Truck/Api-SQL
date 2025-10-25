package com.apisql.ApiSQL.dto.view;

import java.util.Date;

public class RelatorioSimplesViagemDTO {
    private Integer idViagem;
    private String placaCaminhao;
    private Date dataInicioViagem;
    private String kmViagem;
    private Long pontuacaoTotal;
    private Integer idSegmento;
    private Integer idUnidade;
    private Integer idLocalidade;
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

    public Integer getIdSegmento() {
        return idSegmento;
    }

    public void setIdSegmento(Integer idSegmento) {
        this.idSegmento = idSegmento;
    }

    public Integer getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
    }

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public Boolean getWasAnalysed() {
        return wasAnalysed;
    }

    public void setWasAnalysed(Boolean wasAnalysed) {
        this.wasAnalysed = wasAnalysed;
    }
}
