package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.InfracoesMotoristaViagemDTO;
import com.apisql.ApiSQL.dto.view.QuantidadeInfracaoTipoGravidadeDTO;
import com.apisql.ApiSQL.dto.view.VisaoBasicaViagemDTO;
import com.apisql.ApiSQL.repository.view.VisaoBasicaViagemRepository;
import com.apisql.ApiSQL.repository.view.InfracoesMotoristaViagemRepository;
import com.apisql.ApiSQL.repository.view.QuantidadeInfracaoTipoGravidadeRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VisaoBasicaViagemService {

    private final VisaoBasicaViagemRepository visaoBasicaViagemRepository;
    private final InfracoesMotoristaViagemRepository infracoesMotoristaViagemRepository;
    private final QuantidadeInfracaoTipoGravidadeRepository quantidadeInfracaoTipoGravidadeRepository;

    public VisaoBasicaViagemService(
            VisaoBasicaViagemRepository visaoBasicaViagemRepository,
            InfracoesMotoristaViagemRepository infracoesMotoristaViagemRepository,
            QuantidadeInfracaoTipoGravidadeRepository quantidadeInfracaoTipoGravidadeRepository
    ) {
        this.visaoBasicaViagemRepository = visaoBasicaViagemRepository;
        this.infracoesMotoristaViagemRepository = infracoesMotoristaViagemRepository;
        this.quantidadeInfracaoTipoGravidadeRepository = quantidadeInfracaoTipoGravidadeRepository;
    }

    @Transactional(readOnly = true)
    public Optional<VisaoBasicaViagemDTO> findById(Integer id) {

        List<Object[]> resultadoBase = visaoBasicaViagemRepository.buscarVisaoBasicaViagem(id);

        if (resultadoBase.isEmpty()) {
            return Optional.empty();
        }

        Object[] obj = resultadoBase.get(0);
        VisaoBasicaViagemDTO dto = new VisaoBasicaViagemDTO();

        dto.setIdViagem((Integer) obj[0]);
        dto.setPlacaCaminhao((String) obj[1]);
        dto.setDataInicioViagem((java.util.Date) obj[2]);
        dto.setDataFimViagem((java.util.Date) obj[3]);
        dto.setKmViagem((String) obj[4]);
        dto.setIdSegmento((Integer) obj[5]);
        dto.setSegmento((String) obj[6]);
        dto.setIdUnidade((Integer) obj[7]);
        dto.setUnidade((String) obj[8]);
        dto.setIdLocalidade((Integer) obj[9]);

        Integer idViagem = dto.getIdViagem();

        // Mapeamento das Metricas Gerais (Lista)
        List<Object[]> metricasGeraisList = infracoesMotoristaViagemRepository.buscarMetricasGerais(idViagem);
        List<InfracoesMotoristaViagemDTO> metricasGeraisDtos = metricasGeraisList.stream().map(metricas -> {
            InfracoesMotoristaViagemDTO mDto = new InfracoesMotoristaViagemDTO();
            mDto.setIdMotorista((Integer) metricas[0]);
            mDto.setIdViagem((Integer) metricas[1]);
            mDto.setIdUnidade((Integer) metricas[2]);
            mDto.setIdLocalidade((Integer) metricas[3]);
            mDto.setNomeMotorista((String) metricas[4]);
            mDto.setUrlMidiaConcatenada((String) metricas[5]);
            mDto.setRiscoMotorista((String) metricas[6]);
            mDto.setQuantidadeInfracao(((Number) metricas[7]).longValue());
            return mDto;
        }).toList();

        dto.setMetricasGeraisInfracao(metricasGeraisDtos);

        List<Object[]> metricasGravidadeList = quantidadeInfracaoTipoGravidadeRepository.buscarMetricasGravidade(idViagem);
        List<QuantidadeInfracaoTipoGravidadeDTO> metricasGravidadeDtos = metricasGravidadeList.stream().map(metricasGravidade -> {
            QuantidadeInfracaoTipoGravidadeDTO gDto = new QuantidadeInfracaoTipoGravidadeDTO();
            gDto.setIdUnidade((Integer) metricasGravidade[0]);
            gDto.setIdLocalidade((Integer) metricasGravidade[1]);
            gDto.setIdViagem((Integer) metricasGravidade[2]);
            gDto.setIdMotorista((Integer) metricasGravidade[3]);
            gDto.setTipoLeve(((Number) metricasGravidade[4]).longValue());
            gDto.setTipoMedia(((Number) metricasGravidade[5]).longValue());
            gDto.setTipoGrave(((Number) metricasGravidade[6]).longValue());
            gDto.setTipoGravissima(((Number) metricasGravidade[7]).longValue());
            return gDto;
        }).toList();

        dto.setMetricasGravidade(metricasGravidadeDtos);

        return Optional.of(dto);
    }
}