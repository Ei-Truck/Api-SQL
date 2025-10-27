package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.RelatorioSemanalInfracoesDTO;
import com.apisql.ApiSQL.repository.view.RelatorioSemanalInfracoesRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RelatorioSemanalInfracoesService {

    private final RelatorioSemanalInfracoesRepository relatorioSemanalInfracoesRepository;

    public RelatorioSemanalInfracoesService(RelatorioSemanalInfracoesRepository relatorioSemanalInfracoesRepository) {
        this.relatorioSemanalInfracoesRepository = relatorioSemanalInfracoesRepository;
    }

    public List<RelatorioSemanalInfracoesDTO> findAll(HttpServletRequest request) {
        List<Map<String, Object>> resultado = relatorioSemanalInfracoesRepository.buscarRelatorioSemanalInfracoes(request);

        return resultado.stream()
                .map(map -> {
                    RelatorioSemanalInfracoesDTO dto = new RelatorioSemanalInfracoesDTO();
                    dto.setDiasemana(map.get("diasemana").toString());
                    dto.setTotal_infracoes(((Number) map.get("total_infracoes")).intValue());
                    return dto;
                })
                .toList();
    }
}
