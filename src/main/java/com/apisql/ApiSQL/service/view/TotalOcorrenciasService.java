package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.TotalOcorrenciasDTO;
import com.apisql.ApiSQL.repository.view.TotalOcorrenciasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TotalOcorrenciasService {

    private final TotalOcorrenciasRepository repository;

    public TotalOcorrenciasService( TotalOcorrenciasRepository repository){
        this.repository = repository;
    }

    public List<TotalOcorrenciasDTO> findAll(){
        List<Object[]> resultado = repository.buscarTotalOcorrencias();
        return resultado.stream()
                .map(obj ->{
                    TotalOcorrenciasDTO dto = new TotalOcorrenciasDTO();
                    dto.setTotal_ocorrencias(((Number)obj[0]).longValue());
                    dto.setMes(((Number)obj[1]).intValue());
                    dto.setAno(((Number)obj[2]).intValue());
                    return dto;
                }).toList();
    }
}