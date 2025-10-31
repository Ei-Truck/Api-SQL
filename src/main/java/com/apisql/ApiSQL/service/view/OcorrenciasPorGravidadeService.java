package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.OcorrenciasPorGravidadeDTO;
import com.apisql.ApiSQL.repository.view.OcorrenciasPorGravidadeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class OcorrenciasPorGravidadeService {

    private final OcorrenciasPorGravidadeRepository repository;

    public OcorrenciasPorGravidadeService( OcorrenciasPorGravidadeRepository repository){
        this.repository = repository;
    }

    public List<OcorrenciasPorGravidadeDTO> findAll(HttpServletRequest request){
        List<Object[]> resultado = repository.buscarOcorrenciasPorGravidade(request);
        return resultado.stream()
                .map(obj ->{
                    OcorrenciasPorGravidadeDTO dto = new OcorrenciasPorGravidadeDTO();

                    dto.setMes(obj[0] != null ? ((Number)obj[0]).intValue() : 0);
                    dto.setAno(obj[1] != null ? ((Number)obj[1]).intValue() : 0);
                    dto.setIdUnidade(obj[2] != null ? ((Number)obj[2]).intValue() : 0);
                    dto.setIdLocalidade(obj[3] != null ? ((Number)obj[3]).intValue() : 0);

                    if (obj[4] != null) {
                        if (obj[4] instanceof BigInteger) {
                            dto.setTotalOcorrencias(((BigInteger) obj[4]).longValue());
                        } else if (obj[4] instanceof Long) {
                            dto.setTotalOcorrencias((Long) obj[4]);
                        } else if (obj[4] instanceof Number) {
                            dto.setTotalOcorrencias(((Number) obj[4]).longValue());
                        } else {
                            dto.setTotalOcorrencias(0L);
                        }
                    } else {
                        dto.setTotalOcorrencias(0L);
                    }

                    dto.setGravidade(obj[5] != null ? (String)obj[5] : "");
                    return dto;
                }).toList();
    }
}