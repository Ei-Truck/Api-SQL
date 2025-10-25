package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.VisaoBasicaViagemDTO;
import com.apisql.ApiSQL.repository.view.VisaoBasicaViagemRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VisaoBasicaViagemService {
    private VisaoBasicaViagemRepository visaoBasicaViagemRepository;

    public VisaoBasicaViagemService(VisaoBasicaViagemRepository visaoBasicaViagemRepository) {
        this.visaoBasicaViagemRepository = visaoBasicaViagemRepository;
    }

    public List<VisaoBasicaViagemDTO> findALl() {
        List<Object[]> resultado = visaoBasicaViagemRepository.buscarVisaoBasicaViagem();
        return resultado.stream().map(obj -> {
            VisaoBasicaViagemDTO dto = new VisaoBasicaViagemDTO();
            dto.setIdViagem((Integer) obj[0]);
            dto.setPlacaCaminhao((String) obj[1]);
            dto.setDataInicioViagem((Date) obj[2]);
            dto.setDataFimViagem((Date) obj[3]);
            dto.setKmViagem((String) obj[4]);
            dto.setIdSegmento((Integer) obj[5]);
            dto.setSegmento((String) obj[6]);
            dto.setIdUnidade((Integer) obj[7]);
            dto.setUnidade((String) obj[8]);
            dto.setIdLocalidade((Integer) obj[9]);
            dto.setNomeMotorista((String) obj[10]);
            dto.setRiscoMotorista((String) obj[11]);
            dto.setUrlMidiaConcatenada((String) obj[12]);
            dto.setTipoGravidade((String) obj[13]);
            dto.setTipoInfracao((String) obj[14]);

            return dto;
        }).toList();
    }
}
