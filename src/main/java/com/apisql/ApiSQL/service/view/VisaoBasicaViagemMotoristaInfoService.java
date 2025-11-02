package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.VisaoBasicaViagemMotoristaInfoDTO;
import com.apisql.ApiSQL.repository.view.VisaoBasicaViagemMotoristaInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisaoBasicaViagemMotoristaInfoService {
    private final VisaoBasicaViagemMotoristaInfoRepository visaoBasicaViagemMotoristaInfoRepository;

    public VisaoBasicaViagemMotoristaInfoService(
            VisaoBasicaViagemMotoristaInfoRepository visaoBasicaViagemMotoristaInfoRepository
    ) {
        this.visaoBasicaViagemMotoristaInfoRepository = visaoBasicaViagemMotoristaInfoRepository;
    }

    @Transactional
    public List<VisaoBasicaViagemMotoristaInfoDTO> findById(Integer id) {

        List<Object[]> resultadoBase = visaoBasicaViagemMotoristaInfoRepository.buscarInfoMotorista(id);
        List<VisaoBasicaViagemMotoristaInfoDTO> dtos = new ArrayList<>();

        if (resultadoBase.isEmpty()) {
            return dtos;
        }

        for (Object[] obj : resultadoBase) {
            VisaoBasicaViagemMotoristaInfoDTO dto = new VisaoBasicaViagemMotoristaInfoDTO();

            dto.setIdViagem(((Integer)obj[0]));
            dto.setIdMotorista((Integer)obj[1]);
            dto.setIdUnidade((Integer)obj[2]);
            dto.setUnidade((String)obj[3]);
            dto.setIdLocalidade((Integer)obj[4]);
            dto.setNomeMotorista( (String)obj[5]);
            dto.setRiscoMotorista( (String)obj[6]);
            dto.setUrlMidiaConcatenada( (String)obj[7]);
            dto.setUrlFotoMotorista((String)obj[8]);

            dtos.add(dto);
        }

        return dtos;
    }
}