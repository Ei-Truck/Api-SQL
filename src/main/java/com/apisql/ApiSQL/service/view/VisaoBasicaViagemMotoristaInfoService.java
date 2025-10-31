package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.VisaoBasicaViagemMotoristaInfoDTO;
import com.apisql.ApiSQL.repository.view.VisaoBasicaViagemMotoristaInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VisaoBasicaViagemMotoristaInfoService {
    private final VisaoBasicaViagemMotoristaInfoRepository visaoBasicaViagemMotoristaInfoRepository;

    public VisaoBasicaViagemMotoristaInfoService(
            VisaoBasicaViagemMotoristaInfoRepository visaoBasicaViagemMotoristaInfoRepository
    ) {
        this.visaoBasicaViagemMotoristaInfoRepository = visaoBasicaViagemMotoristaInfoRepository;
    }

    @Transactional
    public Optional<VisaoBasicaViagemMotoristaInfoDTO> findById(Integer id) {

        List<Object[]> resultadoBase = visaoBasicaViagemMotoristaInfoRepository.buscarInfoMotorista(id);

        if (resultadoBase.isEmpty()) {
            return Optional.empty();
        }

        Object[] obj = resultadoBase.getFirst();
        VisaoBasicaViagemMotoristaInfoDTO dto = new VisaoBasicaViagemMotoristaInfoDTO();

        dto.setIdViagem(((Integer)obj[0]));
        dto.setIdMotorista((Integer)obj[1]);
        dto.setIdSegmento((Integer)obj[2]);
        dto.setSegmento((String)obj[3]);
        dto.setIdUnidade((Integer)obj[4]);
        dto.setUnidade((String)obj[5]);
        dto.setIdLocalidade((Integer)obj[6]);
        dto.setNomeMotorista( (String)obj[7]);
        dto.setRiscoMotorista( (String)obj[8]);
        dto.setUrlMidiaConcatenada( (String)obj[9]);

        return Optional.of(dto);
    }
}
