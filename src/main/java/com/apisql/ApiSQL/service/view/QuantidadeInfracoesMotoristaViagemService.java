package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.QuantidadeInfracoesViagemMotoristaDTO;
import com.apisql.ApiSQL.repository.view.QuantidadeInfracoesViagemMotoristaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuantidadeInfracoesMotoristaViagemService {
    private final QuantidadeInfracoesViagemMotoristaRepository quantidadeInfracoesViagemMotoristaRepository;

    public QuantidadeInfracoesMotoristaViagemService(QuantidadeInfracoesViagemMotoristaRepository quantidadeInfracoesViagemMotoristaRepository) {
        this.quantidadeInfracoesViagemMotoristaRepository = quantidadeInfracoesViagemMotoristaRepository;
    }
    @Transactional
    public Optional<QuantidadeInfracoesViagemMotoristaDTO> findById(Integer id) {

        List<Object[]> resultadoBase = quantidadeInfracoesViagemMotoristaRepository.buscarInfracoesMotorista(id);

        if (resultadoBase.isEmpty()) {
            return Optional.empty();
        }

        Object[] obj = resultadoBase.getFirst();
        QuantidadeInfracoesViagemMotoristaDTO dto = new QuantidadeInfracoesViagemMotoristaDTO();

        dto.setIdMotorista(((Integer)obj[0]));
        dto.setIdViagem((Integer)obj[1]);
        dto.setQuantidadeInfracoes((Long)obj[2]);

        return Optional.of(dto);
    }
}