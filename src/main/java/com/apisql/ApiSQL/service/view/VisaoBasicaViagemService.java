package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.InfracoesMotoristaViagemDTO;
import com.apisql.ApiSQL.dto.view.QuantidadeInfracaoTipoGravidadeDTO;
import com.apisql.ApiSQL.dto.view.VisaoBasicaViagemDTO;
import com.apisql.ApiSQL.repository.view.VisaoBasicaViagemRepository;
import com.apisql.ApiSQL.repository.view.InfracoesMotoristaViagemRepository;
import com.apisql.ApiSQL.repository.view.QuantidadeInfracaoTipoGravidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

    @Transactional
    public List<VisaoBasicaViagemDTO> findALl(Integer id) {
        List<Object[]> resultadoBase = visaoBasicaViagemRepository.buscarVisaoBasicaViagem(id);

        return resultadoBase.stream().map(obj -> {
            VisaoBasicaViagemDTO dto = new VisaoBasicaViagemDTO();

            dto.setIdViagem((Long) obj[0]);
            dto.setPlacaCaminhao((String) obj[1]);
            dto.setDataInicioViagem((Date) obj[2]);
            dto.setDataFimViagem((Date) obj[3]);
            dto.setKmViagem((String) obj[4]);
            dto.setIdSegmento((Integer) obj[5]);
            dto.setSegmento((String) obj[6]);
            dto.setIdUnidade((Integer) obj[7]);
            dto.setUnidade((String) obj[8]);
            dto.setIdLocalidade((Integer) obj[9]);

            Long idViagem = dto.getIdViagem();

            List<Object[]> metricasList = infracoesMotoristaViagemRepository.buscarMetricasGerais(idViagem);

            List<InfracoesMotoristaViagemDTO> metricasGeraisDtos = metricasList.stream().map(m -> {
                InfracoesMotoristaViagemDTO metricasGeraisDto = new InfracoesMotoristaViagemDTO();
                metricasGeraisDto.setIdMotorista((Integer) m[0]);
                metricasGeraisDto.setIdViagem((Integer) m[1]);
                metricasGeraisDto.setIdUnidade((Integer) m[2]);
                metricasGeraisDto.setIdLocalidade((Integer) m[3]);
                metricasGeraisDto.setNomeMotorista((String) m[4]);
                metricasGeraisDto.setUrlMidiaConcatenada((String) m[5]);
                metricasGeraisDto.setRiscoMotorista((String) m[6]);
                metricasGeraisDto.setQuantidadeInfracao(((Number) m[7]).longValue());
                return metricasGeraisDto;
            }).toList();

            dto.setMetricasGeraisInfracao(metricasGeraisDtos);

            QuantidadeInfracaoTipoGravidadeDTO metricasGravidadeDto = new QuantidadeInfracaoTipoGravidadeDTO();
            List<Object[]> metricasGravidadeList = quantidadeInfracaoTipoGravidadeRepository.buscarMetricasGravidade(idViagem);

            Object[] metricasGravidade = metricasGravidadeList.get(0);
            metricasGravidadeDto.setIdUnidade((Integer) metricasGravidade[0]);
            metricasGravidadeDto.setIdLocalidade((Integer) metricasGravidade[1]);
            metricasGravidadeDto.setIdViagem((Integer) metricasGravidade[2]);
            metricasGravidadeDto.setIdMotorista((Integer) metricasGravidade[3]);
            metricasGravidadeDto.setTipoLeve(((Number) metricasGravidade[4]).longValue());
            metricasGravidadeDto.setTipoMedia(((Number) metricasGravidade[5]).longValue());
            metricasGravidadeDto.setTipoGrave(((Number) metricasGravidade[6]).longValue());
            metricasGravidadeDto.setTipoGravissima(((Number) metricasGravidade[7]).longValue());

            dto.setMetricasGravidade(List.of(metricasGravidadeDto));

            return dto;
        }).toList();
    }

}