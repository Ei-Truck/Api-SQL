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
                    dto.setTipo_infracao(obj[0] != null? ((String)obj[0]) : null);
                    dto.setTotal_ocorrencias(obj[1] != null? ((Long)obj[1]): 0);
                    dto.setPorcentagem_do_total(obj[2] != null? ((BigDecimal)obj[2]): BigDecimal.ZERO);
                    dto.setMes(obj[3] != null? ((BigDecimal)obj[3]): BigDecimal.ZERO);
                    dto.setAno(obj[4] != null? ((BigDecimal)obj[4]): BigDecimal.ZERO);

                    return dto;
                }).toList();
    }

}
