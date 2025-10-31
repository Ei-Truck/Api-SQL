package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.OcorrenciaPorTipoDTO;
import com.apisql.ApiSQL.repository.view.OcorrenciaPorTipoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OcorrenciaPorTipoService {
    private final OcorrenciaPorTipoRepository ocorrenciaPorTipoRepository;

    public OcorrenciaPorTipoService(OcorrenciaPorTipoRepository ocorrenciaPorTipoRepository) {
        this.ocorrenciaPorTipoRepository = ocorrenciaPorTipoRepository;
    }

    public List<OcorrenciaPorTipoDTO> findAll(HttpServletRequest request){
        List<Object[]> resultado = ocorrenciaPorTipoRepository.buscarOcorrenciaPorTipo(request);
        return resultado.stream()
                .map(obj -> {
                    OcorrenciaPorTipoDTO dto = new OcorrenciaPorTipoDTO();

                    dto.setMes(obj[0] != null ? BigDecimal.valueOf(((Number)obj[0]).doubleValue()) : BigDecimal.ZERO);
                    dto.setAno(obj[1] != null ? BigDecimal.valueOf(((Number)obj[1]).doubleValue()) : BigDecimal.ZERO);
                    dto.setIdUnidade(obj[2] != null ? ((Number)obj[2]).intValue() : 0);
                    dto.setIdSegmento(obj[3] != null ? ((Number)obj[3]).intValue() : 0);
                    dto.setIdLocalidade(obj[4] != null ? ((Number)obj[4]).intValue() : 0);
                    dto.setTipo_infracao(obj[5] != null ? ((String)obj[5]) : null);
                    dto.setTotal_ocorrencias(obj[6] != null ? ((Number)obj[6]).longValue() : 0L);
                    dto.setPorcentagem_do_total(obj[7] != null ? BigDecimal.valueOf(((Number)obj[7]).doubleValue()) : BigDecimal.ZERO);
                    dto.setUfEstado(obj[8] != null ? ((String)obj[8]) : null);

                    return dto;
                }).toList();
    }


}
