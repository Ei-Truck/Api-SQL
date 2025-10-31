package com.apisql.ApiSQL.dto.view;

import java.util.Date;

public class VisaoBasicaViagemDTO {
    private Integer idViagem;
    private String placaCaminhao;
    private Date dataInicioViagem;
    private Date dataFimViagem;
    private String kmViagem;

    public VisaoBasicaViagemDTO() {
    }


    public Integer getIdViagem() { return idViagem; }
    public void setIdViagem(Integer idViagem) { this.idViagem = idViagem; }
    public String getPlacaCaminhao() { return placaCaminhao; }
    public void setPlacaCaminhao(String placaCaminhao) { this.placaCaminhao = placaCaminhao; }
    public Date getDataInicioViagem() { return dataInicioViagem; }
    public void setDataInicioViagem(Date dataInicioViagem) { this.dataInicioViagem = dataInicioViagem; }
    public Date getDataFimViagem() { return dataFimViagem; }
    public void setDataFimViagem(Date dataFimViagem) { this.dataFimViagem = dataFimViagem; }
    public String getKmViagem() { return kmViagem; }
    public void setKmViagem(String kmViagem) { this.kmViagem = kmViagem; }

}