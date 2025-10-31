package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.QuantidadeInfracoesViagemMotoristaDTO;
import com.apisql.ApiSQL.repository.view.QuantidadeInfracoesViagemMotoristaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuantidadeInfracoesMotoristaViagemService {
    private final QuantidadeInfracoesViagemMotoristaRepository quantidadeInfracoesViagemMotoristaRepository;

    public QuantidadeInfracoesMotoristaViagemService(QuantidadeInfracoesViagemMotoristaRepository quantidadeInfracoesViagemMotoristaRepository) {
        this.quantidadeInfracoesViagemMotoristaRepository = quantidadeInfracoesViagemMotoristaRepository;
    }
    @Transactional
    public List<QuantidadeInfracoesViagemMotoristaDTO> findById(Integer id) {

        List<Object[]> resultadoBase = quantidadeInfracoesViagemMotoristaRepository.buscarInfracoesMotorista(id);
        List<QuantidadeInfracoesViagemMotoristaDTO> dtos = new ArrayList<>();

        if (resultadoBase.isEmpty()) {
            return dtos;
        }

        for (Object[] obj : resultadoBase) {
            QuantidadeInfracoesViagemMotoristaDTO dto = new QuantidadeInfracoesViagemMotoristaDTO();

            dto.setIdMotorista(((Integer)obj[0]));
            dto.setIdViagem((Integer)obj[1]);
            dto.setQuantidadeInfracoes((Long)obj[2]);

            dtos.add(dto);
        }

        return dtos;
    }
}