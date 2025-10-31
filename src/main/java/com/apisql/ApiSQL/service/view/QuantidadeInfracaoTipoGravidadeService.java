package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.QuantidadeInfracaoTipoGravidadeDTO;
import com.apisql.ApiSQL.repository.view.QuantidadeInfracaoTipoGravidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuantidadeInfracaoTipoGravidadeService {
    private final QuantidadeInfracaoTipoGravidadeRepository quantidadeInfracaoTipoGravidadeRepository;

    public QuantidadeInfracaoTipoGravidadeService(QuantidadeInfracaoTipoGravidadeRepository quantidadeInfracaoTipoGravidadeRepository) {
        this.quantidadeInfracaoTipoGravidadeRepository = quantidadeInfracaoTipoGravidadeRepository;
    }
    @Transactional
    public List<QuantidadeInfracaoTipoGravidadeDTO> findById(Integer id) {

        List<Object[]> resultadoBase = quantidadeInfracaoTipoGravidadeRepository.buscarQntdInfracoes(id);
        List<QuantidadeInfracaoTipoGravidadeDTO> dtos = new ArrayList<>();

        if (resultadoBase.isEmpty()) {
            return dtos;
        }

        for (Object[] obj : resultadoBase) {
            QuantidadeInfracaoTipoGravidadeDTO dto = new QuantidadeInfracaoTipoGravidadeDTO();
            dto.setIdViagem((Integer)obj[0]);
            dto.setIdMotorista((Integer)obj[1]);
            dto.setIdUnidade((Integer)obj[2]);
            dto.setIdLocalidade((Integer) obj[3]);
            dto.setTipoLeve((Long) obj[4]);
            dto.setTipoMedia((Long) obj[5]);
            dto.setTipoGrave((Long) obj[6]);
            dto.setTipoGravissima((Long) obj[7]);

            dtos.add(dto);
        }

        return dtos;
    }
}