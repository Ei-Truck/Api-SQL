package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.OcorrenciasPorGravidadeDTO;
import com.apisql.ApiSQL.repository.view.OcorrenciasPorGravidadeRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

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
                    dto.setMes(((Number)obj[0]).intValue());
                    dto.setAno(((Number)obj[1]).intValue());
                    dto.setIdUnidade(((Number)obj[2]).intValue());
                    dto.setIdSegmento(((Number)obj[3]).intValue());
                    dto.setIdLocalidade(((Number)obj[4]).intValue());
                    dto.setTotal_ocorrencias(((Number)obj[5]).longValue());
                    dto.setGravidade(obj[6].toString());
                    dto.setEstado(obj[7].toString());
                    return dto;
                }).toList();
    }
}