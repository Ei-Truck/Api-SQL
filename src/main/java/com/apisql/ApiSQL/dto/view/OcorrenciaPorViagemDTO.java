package com.apisql.ApiSQL.dto.view;

public class OcorrenciaPorViagemDTO {
    private Integer total_ocorrencia;
    private Integer id_viagem;

    public OcorrenciaPorViagemDTO() {

    }

    public void setId_viagem(Integer id_viagem) {
        this.id_viagem = id_viagem;
    }

    public void setTotal_ocorrencia(Integer total_ocorrencia) {
        this.total_ocorrencia = total_ocorrencia;
    }

    public Integer getTotal_ocorrencia() {
        return total_ocorrencia;
    }

    public Integer getId_viagem() {
        return id_viagem;
    }
}
