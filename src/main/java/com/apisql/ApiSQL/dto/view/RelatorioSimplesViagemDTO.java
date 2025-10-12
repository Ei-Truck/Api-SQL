package com.apisql.ApiSQL.dto.view;

import java.util.Date;

public class RelatorioSimplesViagemDTO {
    private String placa_caminhao;
    private Date data_inicio_viagem;
    private Integer id_viagem;
    private String motorista;
    private Boolean isAnalisada;
    private String km_viagem;
    private Integer pontuacao_total;

    public RelatorioSimplesViagemDTO() {}

    public String getPlaca_caminhao() {
        return placa_caminhao;
    }

    public void setPlaca_caminhao(String placa_caminhao) {
        this.placa_caminhao = placa_caminhao;
    }

    public Date getData_inicio_viagem() {
        return data_inicio_viagem;
    }

    public void setData_inicio_viagem(Date data_inicio_viagem) {
        this.data_inicio_viagem = data_inicio_viagem;
    }


    public Integer getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(Integer id_viagem) {
        this.id_viagem = id_viagem;
    }

    public String getKm_viagem() {
        return km_viagem;
    }

    public void setKm_viagem(String km_viagem) {
        this.km_viagem = km_viagem;
    }

    public String getMotorista() {
        return motorista;
    }

    public Integer getPontuacao_total() {
        return pontuacao_total;
    }

    public Boolean getAnalisada() {
        return isAnalisada;
    }

    public void setAnalisada(Boolean analisada) {
        isAnalisada = analisada;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public void setPontuacao_total(Integer pontuacao_total) {
        this.pontuacao_total = pontuacao_total;
    }
}
