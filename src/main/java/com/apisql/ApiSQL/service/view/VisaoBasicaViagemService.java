package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.VisaoBasicaViagemDTO;
import com.apisql.ApiSQL.repository.view.VisaoBasicaViagemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisaoBasicaViagemService {
    private VisaoBasicaViagemRepository visaoBasicaViagemRepository;

    public VisaoBasicaViagemService(VisaoBasicaViagemRepository visaoBasicaViagemRepository) {
        this.visaoBasicaViagemRepository = visaoBasicaViagemRepository;
    }

    public List<VisaoBasicaViagemDTO> findALl(){
        List<Object[]> resultado = visaoBasicaViagemRepository.buscarVisaoBasicaViagem();
        return resultado.stream().map(obj -> {
            VisaoBasicaViagemDTO dto = new VisaoBasicaViagemDTO();
            dto.setPlacaCaminhao((String) obj[0]);
            dto.setDataInicioViagem((LocalDateTime) obj[1]);
            dto.setDataFimViagem((LocalDateTime) obj[2]);
            dto.setSegmento((String) obj[3]);
            dto.setNomeMotorista((String) obj[4]);
            dto.setRiscoMotorista((String) obj[5]);
            dto.setIdMidiaConcatenada((Integer) obj[6]);
            dto.setUrlMidiaConcatenada((String) obj[7]);
            dto.setIdViagem((Integer) obj[8]);
            dto.setIdSegmento((Integer) obj[9]);
            dto.setIdMotorista((Integer) obj[10]);
            dto.setIdTipoGravidade((Integer) obj[11]);
            dto.setIdTipoRisco((Integer) obj[12]);
            dto.setIdInfracao((Integer) obj[13]);
            dto.setIdCaminhao((Integer) obj[14]);
            return dto;}).toList();
    }
}
