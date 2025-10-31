package com.apisql.ApiSQL.dto.view;

import java.util.Date;
import java.util.List;

public class VisaoBasicaViagemDTO {
    private Long idViagem;
    private String placaCaminhao;
    private Date dataInicioViagem;
    private Date dataFimViagem;
    private String kmViagem;
    private Integer idSegmento;
    private String segmento;
    private Integer idUnidade;
    private String unidade;
    private Integer idLocalidade;
    private List<InfracoesMotoristaViagemDTO> metricasGeraisInfracao;
    private List<QuantidadeInfracaoTipoGravidadeDTO> metricasGravidade;

    public VisaoBasicaViagemDTO() {
    }


    public Long getIdViagem() { return idViagem; }
    public void setIdViagem(Long idViagem) { this.idViagem = idViagem; }
    public String getPlacaCaminhao() { return placaCaminhao; }
    public void setPlacaCaminhao(String placaCaminhao) { this.placaCaminhao = placaCaminhao; }
    public Date getDataInicioViagem() { return dataInicioViagem; }
    public void setDataInicioViagem(Date dataInicioViagem) { this.dataInicioViagem = dataInicioViagem; }
    public Date getDataFimViagem() { return dataFimViagem; }
    public void setDataFimViagem(Date dataFimViagem) { this.dataFimViagem = dataFimViagem; }
    public String getKmViagem() { return kmViagem; }
    public void setKmViagem(String kmViagem) { this.kmViagem = kmViagem; }
    public Integer getIdSegmento() { return idSegmento; }
    public void setIdSegmento(Integer idSegmento) { this.idSegmento = idSegmento; }
    public String getSegmento() { return segmento; }
    public void setSegmento(String segmento) { this.segmento = segmento; }
    public Integer getIdUnidade() { return idUnidade; }
    public void setIdUnidade(Integer idUnidade) { this.idUnidade = idUnidade; }
    public String getUnidade() { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }
    public Integer getIdLocalidade() { return idLocalidade; }
    public void setIdLocalidade(Integer idLocalidade) { this.idLocalidade = idLocalidade; }


    public List<InfracoesMotoristaViagemDTO> getMetricasGeraisInfracao() {
        return metricasGeraisInfracao;
    }

    public void setMetricasGeraisInfracao(List<InfracoesMotoristaViagemDTO> metricasGeraisInfracao) {
        this.metricasGeraisInfracao = metricasGeraisInfracao;
    }

    public List<QuantidadeInfracaoTipoGravidadeDTO> getMetricasGravidade() {
        return metricasGravidade;
    }

    public void setMetricasGravidade(List<QuantidadeInfracaoTipoGravidadeDTO> metricasGravidade) {
        this.metricasGravidade = metricasGravidade;
    }
}