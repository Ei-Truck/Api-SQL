package com.apisql.ApiSQL.service.view;

import com.apisql.ApiSQL.dto.view.MotoristaQuantidadeInfracoesDTO;
import com.apisql.ApiSQL.repository.view.MotoristaQuantidadeInfracoesRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoristaQuantidadeInfracoesService {

    private final MotoristaQuantidadeInfracoesRepository repository;

    public MotoristaQuantidadeInfracoesService( MotoristaQuantidadeInfracoesRepository repository){
        this.repository = repository;
    }

    public List<MotoristaQuantidadeInfracoesDTO> findAll(HttpServletRequest request){
        List<Object[]> resultado = repository.buscarQuantidadeInfracoesMotorista(request);
        return resultado.stream()
                .map(obj ->{
                    MotoristaQuantidadeInfracoesDTO dto = new MotoristaQuantidadeInfracoesDTO();
                    dto.setMes(((Number)obj[0]).intValue());
                    dto.setAno(((Number)obj[1]).intValue());
                    dto.setIdUnidade(((Number)obj[2]).intValue());
                    dto.setIdSegmento(((Number)obj[3]).intValue());
                    dto.setIdLocalidade(((Number)obj[4]).intValue());
                    dto.setMotorista(obj[5].toString());
                    dto.setQuantidade_infracoes(((Number)obj[6]).longValue());
                    dto.setUtfEstado(obj[7].toString());
                    return dto;
                }).toList();
    }
}