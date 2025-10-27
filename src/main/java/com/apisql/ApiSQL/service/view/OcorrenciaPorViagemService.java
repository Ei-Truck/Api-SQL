package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.OcorrenciaPorViagemDTO;
import com.apisql.ApiSQL.repository.view.OcorrenciaPorViagemRepository;
import com.apisql.ApiSQL.repository.view.RelatorioSimplesViagemRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcorrenciaPorViagemService {

    private OcorrenciaPorViagemRepository ocorrenciaPorViagemRepository;

    public OcorrenciaPorViagemService(OcorrenciaPorViagemRepository ocorrenciaPorViagemRepository, RelatorioSimplesViagemRepository relatorioSimplesViagemRepository) {
        this.ocorrenciaPorViagemRepository = ocorrenciaPorViagemRepository;
    }

    public List<OcorrenciaPorViagemDTO> findAll(HttpServletRequest request) {
        List<Object[]> resultado = ocorrenciaPorViagemRepository.buscarOcorrenciaPorViagem(request);
        return resultado.stream()
                .map(obj -> {
                    OcorrenciaPorViagemDTO dto = new OcorrenciaPorViagemDTO();
                    dto.setTotal_ocorrencia(obj[0] != null? ((Number)obj[0]).intValue():0);
                    dto.setId_viagem(obj[1] != null? ((Number)obj[1]).intValue():0);
                    return dto;
                }).toList();
    }
}
