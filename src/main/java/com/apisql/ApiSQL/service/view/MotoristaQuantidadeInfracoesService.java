package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.MotoristaQuantidadeInfracoesDTO;
import com.apisql.ApiSQL.repository.view.MotoristaQuantidadeInfracoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoristaQuantidadeInfracoesService {

    private final MotoristaQuantidadeInfracoesRepository repository;

    public MotoristaQuantidadeInfracoesService( MotoristaQuantidadeInfracoesRepository repository){
        this.repository = repository;
    }

    public List<MotoristaQuantidadeInfracoesDTO> findAll(){
        List<Object[]> resultado = repository.buscarQuantidadeInfracoesMotorista();
        return resultado.stream()
                .map(obj ->{
                    MotoristaQuantidadeInfracoesDTO dto = new MotoristaQuantidadeInfracoesDTO();
                    dto.setMotorista(obj[0].toString());
                    dto.setQuantidade_infracoes(((Number)obj[1]).longValue());
                    dto.setMes(((Number)obj[2]).intValue());
                    dto.setAno(((Number)obj[3]).intValue());
                    return dto;
                }).toList();
    }
}