package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.VisaoBasicaViagemDTO;
import com.apisql.ApiSQL.repository.view.VisaoBasicaViagemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VisaoBasicaViagemService {

    private final VisaoBasicaViagemRepository visaoBasicaViagemRepository;

    public VisaoBasicaViagemService(
            VisaoBasicaViagemRepository visaoBasicaViagemRepository
    ) {
        this.visaoBasicaViagemRepository = visaoBasicaViagemRepository;
    }

    @Transactional
    public Optional<VisaoBasicaViagemDTO> findById(Integer id) {

        List<Object[]> resultadoBase = visaoBasicaViagemRepository.buscarVisaoBasicaViagem(id);

        if (resultadoBase.isEmpty()) {
            return Optional.empty();
        }

        Object[] obj = resultadoBase.getFirst();
        VisaoBasicaViagemDTO dto = new VisaoBasicaViagemDTO();

        dto.setIdViagem((Integer) obj[0]);
        dto.setPlacaCaminhao((String) obj[1]);
        dto.setDataInicioViagem((java.util.Date) obj[2]);
        dto.setDataFimViagem((java.util.Date) obj[3]);
        dto.setKmViagem((String) obj[4]);
        return Optional.of(dto);
    }
}