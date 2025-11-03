package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.TotalOcorrenciasDTO;
import com.apisql.ApiSQL.repository.view.TotalOcorrenciasRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotalOcorrenciasService {

    private final TotalOcorrenciasRepository repository;

    public TotalOcorrenciasService( TotalOcorrenciasRepository repository){
        this.repository = repository;
    }

    public List<TotalOcorrenciasDTO> findAll(HttpServletRequest request){
        List<Object[]> resultado = repository.buscarTotalOcorrencias(request);
        return resultado.stream()
                .map(obj ->{
                    TotalOcorrenciasDTO dto = new TotalOcorrenciasDTO();
                    dto.setMes(((Number)obj[0]).intValue());
                    dto.setAno(((Number)obj[1]).intValue());
                    dto.setIdUnidade(((Number)obj[2]).intValue());
                    dto.setIdSegmento(((Number)obj[3]).intValue());
                    dto.setIdLocalidade(((Number)obj[4]).intValue());
                    dto.setTotal_ocorrencias(((Number)obj[5]).longValue());
                    return dto;
                }).toList();
    }
}