package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.VisaoBasicaViagemDTO;
import com.apisql.ApiSQL.repository.view.VisaoBasicaViagemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
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
            dto.setIdViagem((Integer) obj[0]);
            dto.setPlacaCaminhao((String) obj[1]);
            dto.setDataInicioViagem((Date) obj[2]);
            dto.setDataFimViagem((Date) obj[3]);
            dto.setSegmento((String) obj[4]);
            dto.setNomeMotorista((String) obj[5]);
            dto.setRiscoMotorista((String) obj[6]);
            dto.setUrlMidiaConcatenada((String) obj[7]);
            dto.setTipoGravidade((String) obj[8]);
            dto.setTipoInfracao((String) obj[9]);
            return dto;}).toList();
    }
}
